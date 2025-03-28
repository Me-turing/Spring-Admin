package com.ocbc.les.modules.system.service;

import com.ocbc.les.modules.system.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ocbc.les.modules.system.vo.GetUserRoleVO;

/**
* 针对表【user_role(用户角色关联表)】的数据库操作Service
*/
public interface UserRoleService extends IService<UserRole> {

    GetUserRoleVO getUserRoleListByUserId(String userId);
}
