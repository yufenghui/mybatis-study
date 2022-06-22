package com.yufenghui.mybatis.binding;

import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * MapperProxy 工厂
 * <p>
 * <p/>
 *
 * @author yufenghui
 * @date 2022/6/22 11:21
 */
public class MapperProxyFactory<T> {

    private final Class<T> mapperInterface;

    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public T newInstance(Map<String, String> sqlSession) {
        MapperProxy<T> mapperProxy = new MapperProxy<>(sqlSession, mapperInterface);

        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[]{mapperInterface}, mapperProxy);
    }

}
