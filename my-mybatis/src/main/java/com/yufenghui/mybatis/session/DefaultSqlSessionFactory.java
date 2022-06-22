package com.yufenghui.mybatis.session;

import com.yufenghui.mybatis.binding.MapperRegistry;

/**
 * SqlSessionFactory 默认实现
 * <p/>
 *
 * @author yufenghui
 * @date 2022/6/22 14:15
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final MapperRegistry mapperRegistry;

    public DefaultSqlSessionFactory(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(mapperRegistry);
    }

}
