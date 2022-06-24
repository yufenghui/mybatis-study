package com.yufenghui.mybatis.test;

import com.yufenghui.mybatis.io.Resources;
import com.yufenghui.mybatis.session.SqlSession;
import com.yufenghui.mybatis.session.SqlSessionFactory;
import com.yufenghui.mybatis.session.SqlSessionFactoryBuilder;
import com.yufenghui.mybatis.test.dao.IUserDao;
import org.junit.Test;

import java.io.Reader;

/**
 * ApiTest
 * <p/>
 *
 * @author yufenghui
 * @date 2022/6/22 11:27
 */
public class ApiTest {

    @Test
    public void test_MapperProxy() throws Exception {

        // 1. 基于XML配置 创建SqlSessionFactory
        Reader reader = Resources.getResourceAsReader("mybatis-config-datasource.xml");

        // 2. 从 SqlSession 工厂获取 Session
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        userDao.queryUserInfoById("1");

    }

}
