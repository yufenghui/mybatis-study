package com.yufenghui.mybatis.test.dao;

import com.yufenghui.mybatis.test.po.User;

/**
 * IUserDao
 * <p/>
 *
 * @author yufenghui
 * @date 2022/6/22 11:26
 */
public interface IUserDao {


    User queryUserInfoById(String uId);

}
