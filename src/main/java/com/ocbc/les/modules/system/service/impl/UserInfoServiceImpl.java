package com.ocbc.les.modules.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ocbc.les.common.exception.BusinessException;
import com.ocbc.les.common.util.MessageUtils;
import com.ocbc.les.common.util.RequestContextUtils;
import com.ocbc.les.frame.cache.entity.UserDetailCache;
import com.ocbc.les.frame.cache.util.UserDetailCacheUtils;
import com.ocbc.les.modules.system.dto.GetUserDetailsDTO;
import com.ocbc.les.modules.system.entity.UserInfo;
import com.ocbc.les.modules.system.service.UserInfoService;
import com.ocbc.les.modules.system.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* 针对表【user_info(用户表)】的数据库操作Service实现
*/
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
    implements UserInfoService{

    private final UserInfoMapper userInfoMapper;
    private final UserDetailCacheUtils userDetailCacheUtils;


    @Autowired
    public UserInfoServiceImpl(UserInfoMapper userInfoMapper,UserDetailCacheUtils userDetailCacheUtils) {
        this.userInfoMapper = userInfoMapper;
        this.userDetailCacheUtils = userDetailCacheUtils;
    }

    @Override
    public UserInfo getUserById(String loginId) {
        return userInfoMapper.selectById(loginId);
    }

    @Override
    public void addUser(UserInfo userInfo) {
        userInfoMapper.addUser(userInfo);
    }

    @Override
    public UserDetailCache getUserDetails(GetUserDetailsDTO userDetailsDTO) {
        String requestUserId = RequestContextUtils.getRequestUserId();
        UserDetailCache userDetail = userDetailCacheUtils.getUserDetail(requestUserId);
        if (ObjectUtil.isEmpty(userDetail)){
            throw new BusinessException(MessageUtils.getMessage("auth.login.expired"));
        }
        //获取用户选择的机构详情更新到缓存中

        //获取用户选择的角色详情更新到缓存中

        //获取用户选择的角色权限详情更新到缓存中

        //获取用户选择的额角色菜单更新到缓存中

        return userDetail;
    }

}




