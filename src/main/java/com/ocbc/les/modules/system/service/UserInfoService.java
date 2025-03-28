package com.ocbc.les.modules.system.service;

import com.ocbc.les.frame.cache.entity.UserDetailCache;
import com.ocbc.les.modules.system.dto.GetUserDetailsDTO;
import com.ocbc.les.modules.system.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* 针对表【user_info(用户表)】的数据库操作Service
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

    UserDetailCache getUserDetails(GetUserDetailsDTO userDetailsDTO);
}
