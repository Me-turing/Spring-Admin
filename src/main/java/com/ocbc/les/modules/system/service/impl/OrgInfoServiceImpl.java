package com.ocbc.les.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ocbc.les.modules.system.entity.OrgInfo;
import com.ocbc.les.modules.system.service.OrgInfoService;
import com.ocbc.les.modules.system.mapper.OrgInfoMapper;
import org.springframework.stereotype.Service;

/**
* 针对表【org_info(机构表)】的数据库操作Service实现
*/
@Service
public class OrgInfoServiceImpl extends ServiceImpl<OrgInfoMapper, OrgInfo>
    implements OrgInfoService{

}




