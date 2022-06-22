package com.yufenghui.mybatis.test;

import com.yufenghui.mybatis.binding.MapperProxyFactory;
import com.yufenghui.mybatis.test.dao.IUserDao;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * ApiTest
 * <p/>
 *
 * @author yufenghui
 * @date 2022/6/22 11:27
 */
public class ApiTest {

    @Test
    public void test_MapperProxy() {

        MapperProxyFactory<IUserDao> proxyFactory = new MapperProxyFactory<>(IUserDao.class);
        Map<String, String> sqlSession = new HashMap<>();

        sqlSession.put("com.yufenghui.mybatis.test.dao.IUserDao.queryUserName", "模拟执行 Mapper.xml 中 SQL 语句的操作：查询用户姓名");
        sqlSession.put("com.yufenghui.mybatis.test.dao.IUserDao.queryUserAge", "模拟执行 Mapper.xml 中 SQL 语句的操作：查询用户年龄");

        IUserDao userDao = proxyFactory.newInstance(sqlSession);

        System.out.println(userDao.toString());
        userDao.queryUserName("1");
    }

}
