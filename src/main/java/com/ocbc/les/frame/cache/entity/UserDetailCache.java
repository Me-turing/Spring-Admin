package com.ocbc.les.frame.cache.entity;

import com.ocbc.les.modules.system.entity.UserInfo;
import lombok.Data;

@Data
public class UserCacheInfo {
    /**
     * 用户详情
     */
    private UserInfo userInfo;

    //TODO: 后续应该添加用户所属的权限及菜单
    private Object userDetails;

}
