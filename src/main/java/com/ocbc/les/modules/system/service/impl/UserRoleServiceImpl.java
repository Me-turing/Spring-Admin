package com.ocbc.les.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ocbc.les.modules.system.entity.UserRole;
import com.ocbc.les.modules.system.service.UserRoleService;
import com.ocbc.les.modules.system.mapper.UserRoleMapper;
import org.springframework.stereotype.Service;

/**
* @author 23399
* @description 针对表【user_role(用户角色关联表)】的数据库操作Service实现
* @createDate 2025-03-27 22:34:33
*/
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>
    implements UserRoleService{

}




