package com.yufenghui.mybatis.test;

import cn.hutool.json.JSONUtil;
import com.yufenghui.mybatis.io.Resources;
import com.yufenghui.mybatis.session.SqlSession;
import com.yufenghui.mybatis.session.SqlSessionFactory;
import com.yufenghui.mybatis.session.SqlSessionFactoryBuilder;
import com.yufenghui.mybatis.test.dao.IUserDao;
import com.yufenghui.mybatis.test.po.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Reader;

/**
 * ApiTest
 * <p/>
 *
 * @author yufenghui
 * @date 2022/6/22 11:27
 */
public class ApiTest {

    private static final Logger log = LoggerFactory.getLogger(ApiTest.class);


    @Test
    public void test_Mapper() throws Exception {
        // 1. 基于XML配置 创建SqlSessionFactory
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");

        // 2. 从 SqlSession 工厂获取 Session
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        User user = userDao.queryUserInfoById("1");
        log.info("测试结果：{}", JSONUtil.toJsonStr(user));
    }

    @Test
    public void test_selectOne() throws Exception {
        // 1. 基于XML配置 创建SqlSessionFactory
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");

        // 2. 从 SqlSession 工厂获取 Session
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        Object[] params = {1L};
        Object res = sqlSession.selectOne("com.yufenghui.mybatis.test.dao.IUserDao.queryUserInfoById", params);

        log.info("测试结果：{}", JSONUtil.toJsonStr(res));

    }

}
