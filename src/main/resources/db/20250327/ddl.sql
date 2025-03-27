CREATE TABLE db_key (
    -- 表名
    table_name VARCHAR(100) NOT NULL,
    -- 实体类名/主键
    entity_name VARCHAR(100) NOT NULL,
    -- ID前缀
    prefix VARCHAR(10) NOT NULL,
    -- 日期格式
    date_format VARCHAR(20) NOT NULL DEFAULT 'yyyyMMdd',
    -- 补充位数
    padding_length INT NOT NULL DEFAULT 6,
    -- 批量获取数量
    batch_size INT NOT NULL DEFAULT 100,
    -- 当前起始ID
    current_id BIGINT NOT NULL DEFAULT 1,
    -- 当前起始ID
    record_date DATE,
    -- 当前字母位置(用于扩展容量)
    current_letter_position INT NOT NULL DEFAULT 0,
    -- 最后更新时间
    last_update_time DATETIME NOT NULL DEFAULT GETDATE(),
    -- 唯一索引
    CONSTRAINT uk_db_key_table_name UNIQUE (table_name)
);

-- 创建索引
CREATE INDEX idx_db_key_entity_name ON db_key(table_name);