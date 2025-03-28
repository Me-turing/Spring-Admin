package com.ocbc.les.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ocbc.les.modules.system.entity.RolePermission;
import com.ocbc.les.modules.system.service.RolePermissionService;
import com.ocbc.les.modules.system.mapper.RolePermissionMapper;
import org.springframework.stereotype.Service;

/**
* 针对表【role_permission(角色权限关联表)】的数据库操作Service实现
*/
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission>
    implements RolePermissionService{

}




