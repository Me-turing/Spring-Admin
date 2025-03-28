package com.ocbc.les.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.ocbc.les.common.constant.SystemConstant;
import com.ocbc.les.modules.system.entity.RoleInfo;
import com.ocbc.les.modules.system.entity.UserRole;
import com.ocbc.les.modules.system.service.UserRoleService;
import com.ocbc.les.modules.system.mapper.UserRoleMapper;
import com.ocbc.les.modules.system.vo.GetUserRoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 针对表【user_role(用户角色关联表)】的数据库操作Service实现
*/
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>
    implements UserRoleService{

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public GetUserRoleVO getUserRoleListByUserId(String userId) {
        MPJLambdaWrapper<UserRole> userRoleMPJLambdaWrapper = new MPJLambdaWrapper<>();
        userRoleMPJLambdaWrapper.
                selectAll(RoleInfo.class)
                .eq(UserRole::getUserId,userId)
                .leftJoin(RoleInfo.class,on->
                        on.eq(RoleInfo::getRoleId,UserRole::getRoleId)
                        .eq(RoleInfo::getStatus, SystemConstant.Status.NORMAL))
                .orderByAsc(RoleInfo::getRoleId);
        List<GetUserRoleVO.UserRoleInfo> userRoleInfos = userRoleMapper.selectJoinList(GetUserRoleVO.UserRoleInfo.class, userRoleMPJLambdaWrapper);
        return GetUserRoleVO.builder().userRoleInfos(userRoleInfos).build();
    }
}




