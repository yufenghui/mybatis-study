package com.yufenghui.mybatis.mapping;

import com.yufenghui.mybatis.session.Configuration;

/**
 * mapper方法映射的statement语句
 * <p/>
 *
 * @author yufenghui
 * @date 2022/6/22 18:06
 */
public class MappedStatement {

    private Configuration configuration;

    private String id;

    private SqlCommandType sqlCommandType;

    private String parameterType;

    private String resultType;

    private String sql;


    MappedStatement() {
        // constructor disabled
    }

    public static class Builder {

        private MappedStatement mappedStatement = new MappedStatement();

        public Builder(Configuration configuration, String id, SqlCommandType sqlCommandType, String sql) {
            mappedStatement.configuration = configuration;
            mappedStatement.id = id;
            mappedStatement.sqlCommandType = sqlCommandType;
            mappedStatement.sql = sql;
        }

        public MappedStatement build() {
            assert mappedStatement.configuration != null;
            assert mappedStatement.id != null;
            assert mappedStatement.sqlCommandType != null;
            assert mappedStatement.parameterType != null;
            assert mappedStatement.resultType != null;
            assert mappedStatement.sql != null;

            return mappedStatement;
        }

        public String id() {
            return mappedStatement.id;
        }

        public Builder parameterType(String parameterType) {
            mappedStatement.parameterType = parameterType;
            return this;
        }

        public Builder resultType(String resultType) {
            mappedStatement.resultType = resultType;
            return this;
        }

    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public String getId() {
        return id;
    }

    public SqlCommandType getSqlCommandType() {
        return sqlCommandType;
    }

    public String getParameterType() {
        return parameterType;
    }

    public String getResultType() {
        return resultType;
    }

    public String getSql() {
        return sql;
    }

}
