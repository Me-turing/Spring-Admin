package com.ocbc.les.modules.system.service;

import com.ocbc.les.modules.system.entity.DbKey;

import java.util.Date;

/**
 * 分布式ID配置服务接口
 */
public interface DbKeyService {

    /**
     * 根据表名查询配置
     */
    DbKey getByTableName(String tableName);

    /**
     * 更新当前ID
     */
    void updateCurrentId(String tableName, Long currentId);

    /**
     * 更新当前字母位置
     */
    void updateCurrentLetterPosition(String tableName, Integer currentLetterPosition);

    /**
     * 更新当前日期
     */
    void updateCurrentDate(String tableName, Date recordDate);

    /**
     * 创建配置
     */
    void create(DbKey dbKey);
}