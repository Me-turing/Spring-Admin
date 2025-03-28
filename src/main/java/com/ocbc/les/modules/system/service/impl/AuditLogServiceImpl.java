package com.ocbc.les.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ocbc.les.modules.system.entity.AuditLog;
import com.ocbc.les.modules.system.service.AuditLogService;
import com.ocbc.les.modules.system.mapper.AuditLogMapper;
import org.springframework.stereotype.Service;

/**

* 针对表【audit_log(审计日志表)】的数据库操作Service实现
*/
@Service
public class AuditLogServiceImpl extends ServiceImpl<AuditLogMapper, AuditLog>
    implements AuditLogService{

}




