package com.ocbc.les.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.ocbc.les.common.constant.SystemConstant;
import com.ocbc.les.modules.system.entity.OrgInfo;
import com.ocbc.les.modules.system.entity.UserOrg;
import com.ocbc.les.modules.system.mapper.UserOrgMapper;
import com.ocbc.les.modules.system.service.UserOrgService;
import com.ocbc.les.modules.system.vo.GetUserOrgVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 针对表【user_org(用户机构关联表)】的数据库操作Service实现
*/
@Service
public class UserOrgServiceImpl extends ServiceImpl<UserOrgMapper, UserOrg>
    implements UserOrgService{

    @Autowired
    private UserOrgMapper userOrgMapper;

    @Override
    public GetUserOrgVO getUserOrgListByUserId(String userId) {
        MPJLambdaWrapper<UserOrg> userOrgMPJLambdaQueryWrapper = new MPJLambdaWrapper<>();
        userOrgMPJLambdaQueryWrapper
                .selectAll(OrgInfo.class)
                .eq(UserOrg::getUserId,userId)
                .leftJoin(OrgInfo.class,on-> on.eq(OrgInfo::getOrgId,UserOrg::getOrgId)
                        .eq(OrgInfo::getStatus, SystemConstant.Status.NORMAL))
                .orderByAsc(OrgInfo::getOrgId);
        List<GetUserOrgVO.UserOrgInfo> userOrgInfos = userOrgMapper.selectJoinList(GetUserOrgVO.UserOrgInfo.class, userOrgMPJLambdaQueryWrapper);
        return  GetUserOrgVO.builder().orgInfos(userOrgInfos).build();
    }
}




