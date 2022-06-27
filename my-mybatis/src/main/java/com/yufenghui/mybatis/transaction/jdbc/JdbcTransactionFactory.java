package com.yufenghui.mybatis.transaction.jdbc;

import com.yufenghui.mybatis.transaction.Transaction;
import com.yufenghui.mybatis.transaction.TransactionFactory;
import com.yufenghui.mybatis.transaction.TransactionIsolationLevel;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * JdbcTransaction 工厂
 *
 * @author yufenghui
 * @date 2022/6/25 19:31
 */
public class JdbcTransactionFactory implements TransactionFactory {


    @Override
    public Transaction newTransaction(Connection connection) {
        return new JdbcTransaction(connection);
    }

    @Override
    public Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit) {
        return new JdbcTransaction(dataSource, level, autoCommit);
    }

}
