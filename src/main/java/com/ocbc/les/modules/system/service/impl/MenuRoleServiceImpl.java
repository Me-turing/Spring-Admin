package com.ocbc.les.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ocbc.les.modules.system.entity.MenuRole;
import com.ocbc.les.modules.system.service.MenuRoleService;
import com.ocbc.les.modules.system.mapper.MenuRoleMapper;
import org.springframework.stereotype.Service;

/**
* 针对表【menu_role(菜单角色关联表)】的数据库操作Service实现
*/
@Service
public class MenuRoleServiceImpl extends ServiceImpl<MenuRoleMapper, MenuRole>
    implements MenuRoleService{

}




