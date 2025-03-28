package com.ocbc.les.modules.system.service;

import com.ocbc.les.modules.system.entity.UserOrg;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ocbc.les.modules.system.vo.GetUserOrgVO;

/**
* 针对表【user_org(用户机构关联表)】的数据库操作Service
*/
public interface UserOrgService extends IService<UserOrg> {

    GetUserOrgVO getUserOrgListByUserId(String userId);
}
