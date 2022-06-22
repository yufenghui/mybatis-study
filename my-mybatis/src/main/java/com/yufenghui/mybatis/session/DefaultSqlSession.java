package com.yufenghui.mybatis.session;

import com.yufenghui.mybatis.binding.MapperRegistry;

/**
 * SqlSession 默认实现
 * <p/>
 *
 * @author yufenghui
 * @date 2022/6/22 14:15
 */
public class DefaultSqlSession implements SqlSession {

    /**
     * 映射器注册机
     */
    private final MapperRegistry mapperRegistry;

    public DefaultSqlSession(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    @Override
    public <T> T selectOne(String statement) {
        return selectOne(statement, null);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        System.out.println("你被代理了！" + "方法：" + statement + " 入参：" + parameter);
        return null;
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return mapperRegistry.getMapper(type, this);
    }

}
