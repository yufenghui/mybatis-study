package com.yufenghui.mybatis.binding;

import com.yufenghui.mybatis.session.SqlSession;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Mapper 动态代理
 * <p/>
 *
 * @author yufenghui
 * @date 2022/6/22 10:47
 */
public class MapperProxy<T> implements InvocationHandler, Serializable {

    private static final long serialVersionUID = 1177510625604200742L;

    private SqlSession sqlSession;
    private final Class<T> mapperInterface;

    public MapperProxy(SqlSession sqlSession, Class<T> mapperInterface) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        } else {
            System.out.println("你的被代理了！ " + mapperInterface.getName() + "." + method.getName());
            return null;
        }
    }

}
