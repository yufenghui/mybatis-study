package com.yufenghui.mybatis.test;

import com.yufenghui.mybatis.binding.MapperRegistry;
import com.yufenghui.mybatis.session.DefaultSqlSessionFactory;
import com.yufenghui.mybatis.session.SqlSession;
import com.yufenghui.mybatis.session.SqlSessionFactory;
import com.yufenghui.mybatis.test.dao.IUserDao;
import org.junit.Test;

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

        // 1. 注册 Mapper
        MapperRegistry registry = new MapperRegistry();
        registry.addMappers("com.yufenghui.mybatis.test.dao");

        // 2. 从 SqlSession 工厂获取 Session
        SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(registry);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        userDao.queryUserName("1");
        userDao.queryUserAge("1");

    }

}
