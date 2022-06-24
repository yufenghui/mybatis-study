package com.yufenghui.mybatis.session.defaults;

import com.yufenghui.mybatis.mapping.MappedStatement;
import com.yufenghui.mybatis.session.Configuration;
import com.yufenghui.mybatis.session.SqlSession;

/**
 * SqlSession 默认实现
 * <p/>
 *
 * @author yufenghui
 * @date 2022/6/22 14:15
 */
public class DefaultSqlSession implements SqlSession {

    /**
     * 配置类
     */
    private final Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T selectOne(String statement) {
        return selectOne(statement, null);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        MappedStatement mappedStatement = configuration.getMappedStatement(statement);
        System.out.println("方法：" + statement + "\n入参：" + parameter + "\n待执行SQL：" + mappedStatement.getSql());

        return null;
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.getMapper(type, this);
    }

}
