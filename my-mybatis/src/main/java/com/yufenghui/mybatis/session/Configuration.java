package com.yufenghui.mybatis.session;

import com.yufenghui.mybatis.binding.MapperRegistry;
import com.yufenghui.mybatis.mapping.MappedStatement;

import java.util.HashMap;
import java.util.Map;

/**
 * Configuration 配置类
 * 此处的设计，有点职责混乱，没有做功能拆分
 * <p/>
 *
 * @author yufenghui
 * @date 2022/6/22 18:09
 */
public class Configuration {

    /**
     * 映射注册机
     */
    private MapperRegistry mapperRegistry = new MapperRegistry(this);

    /**
     * mapper方法映射的statement语句
     */
    private final Map<String, MappedStatement> mappedStatements = new HashMap<>();


    public <T> boolean hasMapper(Class<T> type) {
        return mapperRegistry.hasMapper(type);
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        return mapperRegistry.getMapper(type, sqlSession);
    }

    public <T> void addMapper(Class<T> type) {
        mapperRegistry.addMapper(type);
    }

    public void addMappers(String packageName) {
        mapperRegistry.addMappers(packageName);
    }

    public void addMappedStatement(MappedStatement ms) {
        mappedStatements.put(ms.getId(), ms);
    }

    public MappedStatement getMappedStatement(String id) {
        return mappedStatements.get(id);
    }

}
