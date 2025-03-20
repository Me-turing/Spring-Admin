package com.ocbc.les.modules.system.service.impl;

import com.ocbc.les.common.util.Sm4Utils;
import com.ocbc.les.modules.system.entity.UserInfo;
import com.ocbc.les.modules.system.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * 用户详情服务实现类
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private Sm4Utils sm4Utils;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名查询用户信息
        UserInfo userInfo = userInfoService.getUserByLoginId(username);
        if (userInfo == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        
        // 判断用户状态
        if (!"0".equals(userInfo.getStatus())) {
            throw new UsernameNotFoundException("用户已被禁用");
        }
        
        // 返回UserDetails对象,使用SM4加密的密码
        return new User(
            userInfo.getLoginId(),
            userInfo.getPassword(), // 数据库中存储的是SM4加密后的密码
            Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }
} 