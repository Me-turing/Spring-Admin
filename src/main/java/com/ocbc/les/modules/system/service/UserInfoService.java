package com.ocbc.les.modules.system.service;

import com.ocbc.les.modules.system.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 23399
* @description 针对表【user_info(用户表)】的数据库操作Service
* @createDate 2025-03-27 16:33:29
*/
public interface UserInfoService extends IService<UserInfo> {

    /**
     * 根据登录ID查询用户信息
     *
     * @param loginId 用户ID
     * @return 用户信息
     */
    UserInfo getUserById(String loginId);

    void addUser(UserInfo userInfo);

}
