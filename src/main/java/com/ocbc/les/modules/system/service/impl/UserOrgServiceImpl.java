package com.ocbc.les.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ocbc.les.modules.system.entity.UserOrg;
import com.ocbc.les.modules.system.service.UserOrgService;
import com.ocbc.les.modules.system.mapper.UserOrgMapper;
import org.springframework.stereotype.Service;

/**
* @author 23399
* @description 针对表【user_org(用户机构关联表)】的数据库操作Service实现
* @createDate 2025-03-27 22:34:33
*/
@Service
public class UserOrgServiceImpl extends ServiceImpl<UserOrgMapper, UserOrg>
    implements UserOrgService{

}




