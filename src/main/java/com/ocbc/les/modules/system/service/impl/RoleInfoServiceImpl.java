package com.ocbc.les.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ocbc.les.modules.system.entity.RoleInfo;
import com.ocbc.les.modules.system.service.RoleInfoService;
import com.ocbc.les.modules.system.mapper.RoleInfoMapper;
import org.springframework.stereotype.Service;

/**
* 针对表【role_info(角色表)】的数据库操作Service实现
*/
@Service
public class RoleInfoServiceImpl extends ServiceImpl<RoleInfoMapper, RoleInfo>
    implements RoleInfoService{

}




