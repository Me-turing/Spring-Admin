package com.ocbc.les.modules.system.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.ocbc.les.modules.system.entity.DbKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * 分布式ID生成器配置Mapper
 */
@Mapper
public interface DbKeyMapper extends MPJBaseMapper<DbKey> {

    /**
     * 根据表名查询配置
     *
     * @param tableName 表名
     * @return 配置信息
     */
    DbKey selectByTableName(@Param("tableName") String tableName);

    /**
     * 更新当前ID
     *
     * @param tableName 表名
     * @param currentId 当前ID
     * @return 更新结果
     */
    int updateCurrentId(@Param("tableName") String tableName, @Param("currentId") Long currentId);

    /**
     * 更新当前字母位置
     *
     * @param tableName             表名
     * @param currentLetterPosition 当前字母位置
     * @return 更新结果
     */
    int updateCurrentLetterPosition(@Param("tableName") String tableName, @Param("currentLetterPosition") Integer currentLetterPosition);

    /**
     * 更新当前日期
     *
     * @param tableName  表名
     * @param recordDate 当前日期
     * @return 更新结果
     */
    int updateCurrentDate(@Param("tableName") String tableName, @Param("recordDate") Date recordDate);
}



