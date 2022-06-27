package com.yufenghui.mybatis.transaction.jdbc;

import com.yufenghui.mybatis.transaction.Transaction;
import com.yufenghui.mybatis.transaction.TransactionIsolationLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Jdbc事务
 *
 * @author yufenghui
 * @date 2022/6/25 19:31
 */
public class JdbcTransaction implements Transaction {

    private static final Logger log = LoggerFactory.getLogger(JdbcTransaction.class);

    protected DataSource dataSource;
    protected TransactionIsolationLevel level = TransactionIsolationLevel.NONE;
    protected boolean autoCommit;

    protected Connection connection;


    public JdbcTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit) {
        this.dataSource = dataSource;
        this.level = level;
        this.autoCommit = autoCommit;
    }

    public JdbcTransaction(Connection connection) {
        this.connection = connection;
    }


    @Override
    public Connection getConnection() throws SQLException {
        return this.connection;
    }

    @Override
    public void commit() throws SQLException {
        if (this.connection != null && this.connection.getAutoCommit()) {
            this.connection.commit();
        }
    }

    @Override
    public void rollback() throws SQLException {
        if (this.connection != null && this.connection.getAutoCommit()) {
            this.connection.rollback();
        }
    }

    @Override
    public void close() throws SQLException {
        if (this.connection != null) {
            resetAutoCommit();

            this.connection.close();
        }
    }

    protected void resetAutoCommit() {
        try {
            if (!connection.getAutoCommit()) {
                // MyBatis does not call commit/rollback on a connection if just selects were performed.
                // Some databases start transactions with select statements
                // and they mandate a commit/rollback before closing the connection.
                // A workaround is setting the autocommit to true before closing the connection.
                // Sybase throws an exception here.
                if (log.isDebugEnabled()) {
                    log.debug("Resetting autocommit to true on JDBC Connection [" + connection + "]");
                }
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            log.debug("Error resetting autocommit to true "
                    + "before closing the connection.  Cause: " + e);
        }
    }

}
