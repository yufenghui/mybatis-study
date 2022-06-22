package com.yufenghui.mybatis.session;

/**
 * SqlSession Factory
 * <p/>
 *
 * @author yufenghui
 * @date 2022/6/22 14:13
 */
public interface SqlSessionFactory {

    /**
     * 创建一个session会话
     * @return SqlSession
     */
    SqlSession openSession();

}
