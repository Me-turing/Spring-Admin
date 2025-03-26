package com.ocbc.les.modules.system.service.impl;

import com.ocbc.les.modules.system.entity.UserInfo;
import com.ocbc.les.modules.system.mapper.UserInfoMapper;
import com.ocbc.les.modules.system.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户信息服务实现类
 */
@Service
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {

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
    public void updateUser(UserInfo userInfo) {
        userInfoMapper.updateById(userInfo);
    }

    @Override
    public void addUser(UserInfo userInfo) {
        userInfoMapper.addUser(userInfo);
    }
}