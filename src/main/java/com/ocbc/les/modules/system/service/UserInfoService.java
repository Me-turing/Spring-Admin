package com.ocbc.les.modules.system.service;

import com.ocbc.les.modules.system.entity.UserInfo;

/**
 * 用户信息服务接口
 */
public interface UserInfoService {

    /**
     * 根据登录ID查询用户信息
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    UserInfo getUserById(String userId);

    /**
     * 更新用户信息
     *
     * @param userInfo 用户信息
     */
    void updateUser(UserInfo userInfo);

    void addUser(UserInfo userInfo);

}