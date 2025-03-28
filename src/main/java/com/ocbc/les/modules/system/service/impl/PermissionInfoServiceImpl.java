package com.ocbc.les.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ocbc.les.modules.system.entity.PermissionInfo;
import com.ocbc.les.modules.system.service.PermissionInfoService;
import com.ocbc.les.modules.system.mapper.PermissionInfoMapper;
import org.springframework.stereotype.Service;

/**
* 针对表【permission_info(权限表)】的数据库操作Service实现
*/
@Service
public class PermissionInfoServiceImpl extends ServiceImpl<PermissionInfoMapper, PermissionInfo>
    implements PermissionInfoService{

}




