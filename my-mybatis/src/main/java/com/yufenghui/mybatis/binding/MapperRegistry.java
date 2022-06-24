package com.yufenghui.mybatis.binding;

import cn.hutool.core.util.ClassUtil;
import com.yufenghui.mybatis.session.Configuration;
import com.yufenghui.mybatis.session.SqlSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Mapper Registry 注册中心
 * 类似的还有 SpringMVC中的Mapping Registry
 * <p/>
 *
 * @author yufenghui
 * @date 2022/6/22 14:11
 */
public class MapperRegistry {

    private final Configuration configuration;
    private final Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap<>();

    public MapperRegistry(Configuration configuration) {
        this.configuration = configuration;
    }

    public <T> boolean hasMapper(Class<T> type) {
        return knownMappers.containsKey(type);
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) knownMappers.get(type);
        if (mapperProxyFactory == null) {
            throw new RuntimeException("Type " + type + " is not known to the MapperRegistry.");
        }

        return mapperProxyFactory.newInstance(sqlSession);
    }

    public <T> void addMapper(Class<T> type) {
        // Mapper 必须是接口才会注册
        if(type.isInterface()) {
            if(knownMappers.containsKey(type)) {
                // 如果重复添加了，报错
                throw new RuntimeException("Type " + type + " is already known to the MapperRegistry.");
            }
        }

        // 注册映射器代理工厂
        knownMappers.put(type, new MapperProxyFactory<>(type));
    }

    public void addMappers(String packageName) {
        Set<Class<?>> classes = ClassUtil.scanPackage(packageName);
        for (Class<?> clazz : classes) {
            addMapper(clazz);
        }
    }

}
