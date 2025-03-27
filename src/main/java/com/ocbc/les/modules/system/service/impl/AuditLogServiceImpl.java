package com.ocbc.les.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ocbc.les.modules.system.entity.AuditLog;
import com.ocbc.les.modules.system.service.AuditLogService;
import com.ocbc.les.modules.system.mapper.AuditLogMapper;
import org.springframework.stereotype.Service;

/**
* @author 23399
* @description 针对表【audit_log(审计日志表)】的数据库操作Service实现
* @createDate 2025-03-27 22:34:33
*/
@Service
public class AuditLogServiceImpl extends ServiceImpl<AuditLogMapper, AuditLog>
    implements AuditLogService{

}




