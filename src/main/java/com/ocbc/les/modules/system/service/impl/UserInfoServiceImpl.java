package com.ocbc.les.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ocbc.les.modules.system.entity.UserInfo;
import com.ocbc.les.modules.system.service.UserInfoService;
import com.ocbc.les.modules.system.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author 23399
* @description 针对表【user_info(用户表)】的数据库操作Service实现
* @createDate 2025-03-27 16:33:29
*/
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
    implements UserInfoService{

    private final UserInfoMapper userInfoMapper;
    @Autowired
    public UserInfoServiceImpl(UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }

    @Override
    public UserInfo getUserById(String loginId) {
        return userInfoMapper.selectById(loginId);
    }

    @Override
    public void addUser(UserInfo userInfo) {
        userInfoMapper.addUser(userInfo);
    }

}




