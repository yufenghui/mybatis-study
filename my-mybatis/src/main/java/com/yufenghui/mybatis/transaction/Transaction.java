package com.yufenghui.mybatis.transaction;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 事务，包装了一个Connection, 包含commit、rollback、close方法
 *
 * 在 MyBatis 中有两种事务管理器类型(也就是 type=”[JDBC|MANAGED]”)
 *
 * @author yufenghui
 * @date 2022/6/25 18:05
 */
public interface Transaction {


    Connection getConnection() throws SQLException;

    void commit() throws SQLException;

    void rollback() throws SQLException;

    void close() throws SQLException;

}
