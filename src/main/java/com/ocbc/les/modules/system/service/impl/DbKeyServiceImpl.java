package com.ocbc.les.modules.system.service.impl;

import com.ocbc.les.modules.system.entity.DbKey;
import com.ocbc.les.modules.system.mapper.DbKeyMapper;
import com.ocbc.les.modules.system.service.DbKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 分布式ID配置服务实现
 */
@Service
public class DbKeyServiceImpl implements DbKeyService {

    private final DbKeyMapper dbKeyMapper;

    @Autowired
    public DbKeyServiceImpl(DbKeyMapper dbKeyMapper) {
        this.dbKeyMapper = dbKeyMapper;
    }

    @Override
    public DbKey getByTableName(String tableName) {
        return dbKeyMapper.selectByTableName(tableName);
    }

    @Override
    public void updateCurrentId(String tableName, Long currentId) {
        dbKeyMapper.updateCurrentId(tableName, currentId);
    }

    @Override
    public void updateCurrentLetterPosition(String tableName, Integer currentLetterPosition) {
        dbKeyMapper.updateCurrentLetterPosition(tableName, currentLetterPosition);
    }

    @Override
    public void updateCurrentDate(String tableName, Date recordDate) {
        dbKeyMapper.updateCurrentDate(tableName, recordDate);
    }

    @Override
    public void create(DbKey dbKey) {
        dbKeyMapper.insert(dbKey);
    }
}
