package com.yufenghui.mybatis.session.defaults;

import com.yufenghui.mybatis.session.Configuration;
import com.yufenghui.mybatis.session.SqlSession;
import com.yufenghui.mybatis.session.SqlSessionFactory;

/**
 * SqlSessionFactory 默认实现
 * <p/>
 *
 * @author yufenghui
 * @date 2022/6/22 14:15
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    /**
     * 配置类
     */
    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }

}
