package com.yufenghui.mybatis.mapping;

import com.yufenghui.mybatis.transaction.TransactionFactory;

import javax.sql.DataSource;

/**
 * 环境
 *
 * @author yufenghui
 * @date 2022/6/25 22:38
 */
public final class Environment {

    /**
     * 环境ID
     */
    private final String id;

    /**
     * 事务工厂
     */
    private final TransactionFactory transactionFactory;

    /**
     * 数据源
     */
    private final DataSource dataSource;


    public Environment(String id, TransactionFactory transactionFactory, DataSource dataSource) {
        if (id == null) {
            throw new IllegalArgumentException("Parameter 'id' must not be null");
        }
        if (transactionFactory == null) {
            throw new IllegalArgumentException("Parameter 'transactionFactory' must not be null");
        }
        this.id = id;
        if (dataSource == null) {
            throw new IllegalArgumentException("Parameter 'dataSource' must not be null");
        }

        this.transactionFactory = transactionFactory;
        this.dataSource = dataSource;
    }

    /**
     * 建造者
     */
    public static class Builder {

        private String id;

        private TransactionFactory transactionFactory;

        private DataSource dataSource;

        public Builder(String id) {
            this.id = id;
        }

        public String id() {
            return this.id;
        }

        public Builder transactionFactory(TransactionFactory transactionFactory) {
            this.transactionFactory = transactionFactory;
            return this;
        }

        public Builder dataSource(DataSource dataSource) {
            this.dataSource = dataSource;
            return this;
        }

        public Environment build() {
            return new Environment(id, transactionFactory, dataSource);
        }
    }

    public String getId() {
        return id;
    }

    public TransactionFactory getTransactionFactory() {
        return transactionFactory;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

}
