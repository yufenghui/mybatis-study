package com.yufenghui.mybatis.test.dao;

/**
 * IUserDao
 * <p/>
 *
 * @author yufenghui
 * @date 2022/6/22 11:26
 */
public interface IUserDao {

    String queryUserName(String uId);

    Integer queryUserAge(String uId);

}