-- 删除表(注意删除顺序要考虑外键约束)

-- 创建用户表
CREATE TABLE user_info (
   user_id NVARCHAR(32) PRIMARY KEY,
   login_id NVARCHAR(32) NOT NULL,
   password NVARCHAR(100) NOT NULL,
   user_name_zh NVARCHAR(50),
   user_name_en NVARCHAR(50),
   status CHAR(1) DEFAULT '0',
   del_flag CHAR(1) DEFAULT '0',
   create_by NVARCHAR(32),
   create_org_id NVARCHAR(32),
   create_time DATETIME DEFAULT GETDATE(),
   update_by NVARCHAR(32),
   update_org_id NVARCHAR(32),
   update_time DATETIME DEFAULT GETDATE(),
   remark NVARCHAR(500)
);

-- 添加表注释
EXEC sp_addextendedproperty N'MS_Description', N'用户表', N'SCHEMA', N'dbo', N'TABLE', N'user_info';

-- 添加列注释
EXEC sp_addextendedproperty N'MS_Description', N'用户ID', N'SCHEMA', N'dbo', N'TABLE', N'user_info', N'COLUMN', N'user_id';
EXEC sp_addextendedproperty N'MS_Description', N'登录ID', N'SCHEMA', N'dbo', N'TABLE', N'user_info', N'COLUMN', N'login_id';
EXEC sp_addextendedproperty N'MS_Description', N'密码', N'SCHEMA', N'dbo', N'TABLE', N'user_info', N'COLUMN', N'password';
EXEC sp_addextendedproperty N'MS_Description', N'中文名', N'SCHEMA', N'dbo', N'TABLE', N'user_info', N'COLUMN', N'user_name_zh';
EXEC sp_addextendedproperty N'MS_Description', N'英文名', N'SCHEMA', N'dbo', N'TABLE', N'user_info', N'COLUMN', N'user_name_en';
EXEC sp_addextendedproperty N'MS_Description', N'状态（0：正常，1：停用）', N'SCHEMA', N'dbo', N'TABLE', N'user_info', N'COLUMN', N'status';
EXEC sp_addextendedproperty N'MS_Description', N'删除标志（0：存在，1：删除）', N'SCHEMA', N'dbo', N'TABLE', N'user_info', N'COLUMN', N'del_flag';
EXEC sp_addextendedproperty N'MS_Description', N'创建者', N'SCHEMA', N'dbo', N'TABLE', N'user_info', N'COLUMN', N'create_by';
EXEC sp_addextendedproperty N'MS_Description', N'创建者机构ID', N'SCHEMA', N'dbo', N'TABLE', N'user_info', N'COLUMN', N'create_org_id';
EXEC sp_addextendedproperty N'MS_Description', N'创建时间', N'SCHEMA', N'dbo', N'TABLE', N'user_info', N'COLUMN', N'create_time';
EXEC sp_addextendedproperty N'MS_Description', N'更新者', N'SCHEMA', N'dbo', N'TABLE', N'user_info', N'COLUMN', N'update_by';
EXEC sp_addextendedproperty N'MS_Description', N'更新者机构ID', N'SCHEMA', N'dbo', N'TABLE', N'user_info', N'COLUMN', N'update_org_id';
EXEC sp_addextendedproperty N'MS_Description', N'更新时间', N'SCHEMA', N'dbo', N'TABLE', N'user_info', N'COLUMN', N'update_time';
EXEC sp_addextendedproperty N'MS_Description', N'备注', N'SCHEMA', N'dbo', N'TABLE', N'user_info', N'COLUMN', N'remark';


-- 审计表
-- 这两个需要在审计表中记录
-- last_login_time DATETIME,
-- last_login_ip NVARCHAR(50),


-- IF EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[role_info]') AND type in (N'U'))
--     DROP TABLE [dbo].[role_info];
--
-- IF EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[permission_info]') AND type in (N'U'))
--     DROP TABLE [dbo].[permission_info];
--
-- IF EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[menu_info]') AND type in (N'U'))
--     DROP TABLE [dbo].[menu_info];
--
-- IF EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[org_info]') AND type in (N'U'))
--     DROP TABLE [dbo].[org_info];
--
-- IF EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[user_role]') AND type in (N'U'))
--     DROP TABLE [dbo].[user_role];
--
-- IF EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[role_permission]') AND type in (N'U'))
--     DROP TABLE [dbo].[role_permission];
--
-- IF EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[role_menu]') AND type in (N'U'))
--     DROP TABLE [dbo].[role_menu];


