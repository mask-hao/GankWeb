package com.zhanghao.service;

import com.zhanghao.entity.User;

/**
 * Created by 张浩 on 2017/1/20.
 */
public interface UserService {

    /**
     * 用户登录成功后返回Token
     * @param user user实体
     * @return token
     */
    boolean  login(User user);


    /**
     * 用户是否注册成功
     * @param account 账户
     * @param password 密码
     * @param username 用户名
     * @return 是否注册成功
     */
    int register(String account,String password,String username);


    /**
     * 用户登出
     * @param token 用户凭证
     * @return 是否登出成功
     */
    boolean logout(String token);



    /**
     * 检查用户是否合法
     * @param userToken userId
     * @return 用户是否合法
     */
    boolean checkUser(String userToken);


    /**
     * 获取一个完整的用户
     * @param userAccount 用户账号
     * @return user实体
     */
    User getUserByUserAccount(String userAccount);


    /**
     * 移除一个用户
     * @param userAccount 用户账号
     * @return 是否移除
     */
    boolean removeUserByUserAccount(String userAccount);


    /**
     * 获取一个完整的用户实体
     * @param token 用户凭证
     * @return user实体
     */
    User getUserByUserToken(String token);

    /**
     * 更新用户头像
     * @param token 用户凭证
     */
    boolean updateUserImageByUserAccount(String token,String image);


    /**
     * 更新用户信息
     * @param token 用户凭证
     * @param user 新的用户实体
     * @return 是否更新成功
     */
    boolean updateUserInfoByUserAccount(String token,User user);


    /**
     * 更新用户池中的用户
     * @param token 用户凭证
     * @param user 更新的用户
     */
    void updateUserInUserPool(String token,User user);


    /**
     * 用户修改密码
     * @param account 用户账号
     * @param newPass 新密码
     * @return 是否更新成功
     */
    boolean updateUserPassword(String account ,String newPass);


    /**
     *
     * @param userAccount 用户账号
     * @return 用户密码
     */
    String getUserPassword(String userAccount);




}
