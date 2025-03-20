package com.ocbc.les.modules.system.service.impl;

import com.ocbc.les.modules.system.entity.UserInfo;
import com.ocbc.les.modules.system.mapper.UserInfoMapper;
import com.ocbc.les.modules.system.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户信息服务实现类
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfo getUserByLoginId(String loginId) {
        return userInfoMapper.selectByLoginId(loginId);
    }

    @Override
    public UserInfo getUserById(Long id) {
        return userInfoMapper.selectById(id);
    }

    @Override
    public void updateUser(UserInfo userInfo) {
        userInfoMapper.updateById(userInfo);
    }
} 