-- 删除表(注意删除顺序要考虑外键约束)
IF EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[user_role]') AND type in (N'U'))
    DROP TABLE [dbo].[user_role];

IF EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[role_permission]') AND type in (N'U'))
    DROP TABLE [dbo].[role_permission];

IF EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[role_menu]') AND type in (N'U'))
    DROP TABLE [dbo].[role_menu];

IF EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[user_info]') AND type in (N'U'))
    DROP TABLE [dbo].[user_info];

IF EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[role_info]') AND type in (N'U'))
    DROP TABLE [dbo].[role_info];

IF EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[permission_info]') AND type in (N'U'))
    DROP TABLE [dbo].[permission_info];

IF EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[menu_info]') AND type in (N'U'))
    DROP TABLE [dbo].[menu_info];

IF EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[org_info]') AND type in (N'U'))
    DROP TABLE [dbo].[org_info];

-- 创建机构表
CREATE TABLE org_info (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    org_code NVARCHAR(50) NOT NULL UNIQUE,
    org_name_zh NVARCHAR(100) NOT NULL,
    org_name_en NVARCHAR(100) NOT NULL,
    parent_id BIGINT DEFAULT 0,
    org_type CHAR(1) NOT NULL,
    org_sort INT DEFAULT 0,
    status CHAR(1) DEFAULT '0',
    del_flag CHAR(1) DEFAULT '0',
    create_by NVARCHAR(50),
    create_org_id BIGINT,
    create_time DATETIME DEFAULT GETDATE(),
    update_by NVARCHAR(50),
    update_org_id BIGINT,
    update_time DATETIME DEFAULT GETDATE(),
    remark NVARCHAR(500)
);

-- 添加表注释
EXEC sp_addextendedproperty N'MS_Description', N'机构表', N'SCHEMA', N'dbo', N'TABLE', N'org_info';

-- 添加列注释
EXEC sp_addextendedproperty N'MS_Description', N'机构编码', N'SCHEMA', N'dbo', N'TABLE', N'org_info', N'COLUMN', N'org_code';
EXEC sp_addextendedproperty N'MS_Description', N'机构名称(中文)', N'SCHEMA', N'dbo', N'TABLE', N'org_info', N'COLUMN', N'org_name_zh';
EXEC sp_addextendedproperty N'MS_Description', N'机构名称(英文)', N'SCHEMA', N'dbo', N'TABLE', N'org_info', N'COLUMN', N'org_name_en';
EXEC sp_addextendedproperty N'MS_Description', N'父机构ID', N'SCHEMA', N'dbo', N'TABLE', N'org_info', N'COLUMN', N'parent_id';
EXEC sp_addextendedproperty N'MS_Description', N'机构类型（1：总部，2：分行，3：支行，4：部门）', N'SCHEMA', N'dbo', N'TABLE', N'org_info', N'COLUMN', N'org_type';
EXEC sp_addextendedproperty N'MS_Description', N'显示顺序', N'SCHEMA', N'dbo', N'TABLE', N'org_info', N'COLUMN', N'org_sort';
EXEC sp_addextendedproperty N'MS_Description', N'状态（0：正常，1：停用）', N'SCHEMA', N'dbo', N'TABLE', N'org_info', N'COLUMN', N'status';
EXEC sp_addextendedproperty N'MS_Description', N'删除标志（0：存在，1：删除）', N'SCHEMA', N'dbo', N'TABLE', N'org_info', N'COLUMN', N'del_flag';
EXEC sp_addextendedproperty N'MS_Description', N'创建者', N'SCHEMA', N'dbo', N'TABLE', N'org_info', N'COLUMN', N'create_by';
EXEC sp_addextendedproperty N'MS_Description', N'创建者机构ID', N'SCHEMA', N'dbo', N'TABLE', N'org_info', N'COLUMN', N'create_org_id';
EXEC sp_addextendedproperty N'MS_Description', N'创建时间', N'SCHEMA', N'dbo', N'TABLE', N'org_info', N'COLUMN', N'create_time';
EXEC sp_addextendedproperty N'MS_Description', N'更新者', N'SCHEMA', N'dbo', N'TABLE', N'org_info', N'COLUMN', N'update_by';
EXEC sp_addextendedproperty N'MS_Description', N'更新者机构ID', N'SCHEMA', N'dbo', N'TABLE', N'org_info', N'COLUMN', N'update_org_id';
EXEC sp_addextendedproperty N'MS_Description', N'更新时间', N'SCHEMA', N'dbo', N'TABLE', N'org_info', N'COLUMN', N'update_time';
EXEC sp_addextendedproperty N'MS_Description', N'备注', N'SCHEMA', N'dbo', N'TABLE', N'org_info', N'COLUMN', N'remark';

-- 创建用户表
CREATE TABLE user_info (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    login_id NVARCHAR(50) NOT NULL UNIQUE,
    password NVARCHAR(100) NOT NULL,
    user_name_zh NVARCHAR(50),
    user_name_en NVARCHAR(50),
    org_id BIGINT,
    status CHAR(1) DEFAULT '0',
    last_login_time DATETIME,
    last_login_ip NVARCHAR(50),
    del_flag CHAR(1) DEFAULT '0',
    create_by NVARCHAR(50),
    create_org_id BIGINT,
    create_time DATETIME DEFAULT GETDATE(),
    update_by NVARCHAR(50),
    update_org_id BIGINT,
    update_time DATETIME DEFAULT GETDATE(),
    remark NVARCHAR(500)
);

-- 添加表注释
EXEC sp_addextendedproperty N'MS_Description', N'用户表', N'SCHEMA', N'dbo', N'TABLE', N'user_info';

-- 添加列注释
EXEC sp_addextendedproperty N'MS_Description', N'登录ID', N'SCHEMA', N'dbo', N'TABLE', N'user_info', N'COLUMN', N'login_id';
EXEC sp_addextendedproperty N'MS_Description', N'密码', N'SCHEMA', N'dbo', N'TABLE', N'user_info', N'COLUMN', N'password';
EXEC sp_addextendedproperty N'MS_Description', N'中文名', N'SCHEMA', N'dbo', N'TABLE', N'user_info', N'COLUMN', N'user_name_zh';
EXEC sp_addextendedproperty N'MS_Description', N'英文名', N'SCHEMA', N'dbo', N'TABLE', N'user_info', N'COLUMN', N'user_name_en';
EXEC sp_addextendedproperty N'MS_Description', N'所属机构ID', N'SCHEMA', N'dbo', N'TABLE', N'user_info', N'COLUMN', N'org_id';
EXEC sp_addextendedproperty N'MS_Description', N'状态（0：正常，1：停用）', N'SCHEMA', N'dbo', N'TABLE', N'user_info', N'COLUMN', N'status';
EXEC sp_addextendedproperty N'MS_Description', N'最后登录时间', N'SCHEMA', N'dbo', N'TABLE', N'user_info', N'COLUMN', N'last_login_time';
EXEC sp_addextendedproperty N'MS_Description', N'最后登录IP', N'SCHEMA', N'dbo', N'TABLE', N'user_info', N'COLUMN', N'last_login_ip';
EXEC sp_addextendedproperty N'MS_Description', N'删除标志（0：存在，1：删除）', N'SCHEMA', N'dbo', N'TABLE', N'user_info', N'COLUMN', N'del_flag';
EXEC sp_addextendedproperty N'MS_Description', N'创建者', N'SCHEMA', N'dbo', N'TABLE', N'user_info', N'COLUMN', N'create_by';
EXEC sp_addextendedproperty N'MS_Description', N'创建者机构ID', N'SCHEMA', N'dbo', N'TABLE', N'user_info', N'COLUMN', N'create_org_id';
EXEC sp_addextendedproperty N'MS_Description', N'创建时间', N'SCHEMA', N'dbo', N'TABLE', N'user_info', N'COLUMN', N'create_time';
EXEC sp_addextendedproperty N'MS_Description', N'更新者', N'SCHEMA', N'dbo', N'TABLE', N'user_info', N'COLUMN', N'update_by';
EXEC sp_addextendedproperty N'MS_Description', N'更新者机构ID', N'SCHEMA', N'dbo', N'TABLE', N'user_info', N'COLUMN', N'update_org_id';
EXEC sp_addextendedproperty N'MS_Description', N'更新时间', N'SCHEMA', N'dbo', N'TABLE', N'user_info', N'COLUMN', N'update_time';
EXEC sp_addextendedproperty N'MS_Description', N'备注', N'SCHEMA', N'dbo', N'TABLE', N'user_info', N'COLUMN', N'remark';

-- 创建角色表
CREATE TABLE role_info (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    role_name NVARCHAR(50) NOT NULL,
    role_key NVARCHAR(50) NOT NULL,
    role_sort INT DEFAULT 0,
    status CHAR(1) DEFAULT '0',
    del_flag CHAR(1) DEFAULT '0',
    create_by NVARCHAR(50),
    create_org_id BIGINT,
    create_time DATETIME DEFAULT GETDATE(),
    update_by NVARCHAR(50),
    update_org_id BIGINT,
    update_time DATETIME DEFAULT GETDATE(),
    remark NVARCHAR(500)
);

-- 添加表注释
EXEC sp_addextendedproperty N'MS_Description', N'角色表', N'SCHEMA', N'dbo', N'TABLE', N'role_info';

-- 添加列注释
EXEC sp_addextendedproperty N'MS_Description', N'角色名称', N'SCHEMA', N'dbo', N'TABLE', N'role_info', N'COLUMN', N'role_name';
EXEC sp_addextendedproperty N'MS_Description', N'角色标识', N'SCHEMA', N'dbo', N'TABLE', N'role_info', N'COLUMN', N'role_key';
EXEC sp_addextendedproperty N'MS_Description', N'显示顺序', N'SCHEMA', N'dbo', N'TABLE', N'role_info', N'COLUMN', N'role_sort';
EXEC sp_addextendedproperty N'MS_Description', N'状态（0：正常，1：停用）', N'SCHEMA', N'dbo', N'TABLE', N'role_info', N'COLUMN', N'status';
EXEC sp_addextendedproperty N'MS_Description', N'删除标志（0：存在，1：删除）', N'SCHEMA', N'dbo', N'TABLE', N'role_info', N'COLUMN', N'del_flag';
EXEC sp_addextendedproperty N'MS_Description', N'创建者', N'SCHEMA', N'dbo', N'TABLE', N'role_info', N'COLUMN', N'create_by';
EXEC sp_addextendedproperty N'MS_Description', N'创建者机构ID', N'SCHEMA', N'dbo', N'TABLE', N'role_info', N'COLUMN', N'create_org_id';
EXEC sp_addextendedproperty N'MS_Description', N'创建时间', N'SCHEMA', N'dbo', N'TABLE', N'role_info', N'COLUMN', N'create_time';
EXEC sp_addextendedproperty N'MS_Description', N'更新者', N'SCHEMA', N'dbo', N'TABLE', N'role_info', N'COLUMN', N'update_by';
EXEC sp_addextendedproperty N'MS_Description', N'更新者机构ID', N'SCHEMA', N'dbo', N'TABLE', N'role_info', N'COLUMN', N'update_org_id';
EXEC sp_addextendedproperty N'MS_Description', N'更新时间', N'SCHEMA', N'dbo', N'TABLE', N'role_info', N'COLUMN', N'update_time';
EXEC sp_addextendedproperty N'MS_Description', N'备注', N'SCHEMA', N'dbo', N'TABLE', N'role_info', N'COLUMN', N'remark';

-- 创建权限表
CREATE TABLE permission_info (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    permission_name NVARCHAR(50) NOT NULL,
    permission_key NVARCHAR(50) NOT NULL,
    permission_sort INT DEFAULT 0,
    status CHAR(1) DEFAULT '0',
    del_flag CHAR(1) DEFAULT '0',
    create_by NVARCHAR(50),
    create_org_id BIGINT,
    create_time DATETIME DEFAULT GETDATE(),
    update_by NVARCHAR(50),
    update_org_id BIGINT,
    update_time DATETIME DEFAULT GETDATE(),
    remark NVARCHAR(500)
);

-- 添加表注释
EXEC sp_addextendedproperty N'MS_Description', N'权限表', N'SCHEMA', N'dbo', N'TABLE', N'permission_info';

-- 添加列注释
EXEC sp_addextendedproperty N'MS_Description', N'权限名称', N'SCHEMA', N'dbo', N'TABLE', N'permission_info', N'COLUMN', N'permission_name';
EXEC sp_addextendedproperty N'MS_Description', N'权限标识（模块:操作，如：system:user:add）', N'SCHEMA', N'dbo', N'TABLE', N'permission_info', N'COLUMN', N'permission_key';
EXEC sp_addextendedproperty N'MS_Description', N'显示顺序', N'SCHEMA', N'dbo', N'TABLE', N'permission_info', N'COLUMN', N'permission_sort';
EXEC sp_addextendedproperty N'MS_Description', N'状态（0：正常，1：停用）', N'SCHEMA', N'dbo', N'TABLE', N'permission_info', N'COLUMN', N'status';
EXEC sp_addextendedproperty N'MS_Description', N'删除标志（0：存在，1：删除）', N'SCHEMA', N'dbo', N'TABLE', N'permission_info', N'COLUMN', N'del_flag';
EXEC sp_addextendedproperty N'MS_Description', N'创建者', N'SCHEMA', N'dbo', N'TABLE', N'permission_info', N'COLUMN', N'create_by';
EXEC sp_addextendedproperty N'MS_Description', N'创建者机构ID', N'SCHEMA', N'dbo', N'TABLE', N'permission_info', N'COLUMN', N'create_org_id';
EXEC sp_addextendedproperty N'MS_Description', N'创建时间', N'SCHEMA', N'dbo', N'TABLE', N'permission_info', N'COLUMN', N'create_time';
EXEC sp_addextendedproperty N'MS_Description', N'更新者', N'SCHEMA', N'dbo', N'TABLE', N'permission_info', N'COLUMN', N'update_by';
EXEC sp_addextendedproperty N'MS_Description', N'更新者机构ID', N'SCHEMA', N'dbo', N'TABLE', N'permission_info', N'COLUMN', N'update_org_id';
EXEC sp_addextendedproperty N'MS_Description', N'更新时间', N'SCHEMA', N'dbo', N'TABLE', N'permission_info', N'COLUMN', N'update_time';
EXEC sp_addextendedproperty N'MS_Description', N'备注', N'SCHEMA', N'dbo', N'TABLE', N'permission_info', N'COLUMN', N'remark';

-- 创建菜单表
CREATE TABLE menu_info (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    parent_id BIGINT DEFAULT 0,
    menu_name NVARCHAR(50) NOT NULL,
    menu_key NVARCHAR(50),
    menu_url NVARCHAR(200),
    menu_icon NVARCHAR(100),
    menu_sort INT DEFAULT 0,
    status CHAR(1) DEFAULT '0',
    del_flag CHAR(1) DEFAULT '0',
    create_by NVARCHAR(50),
    create_org_id BIGINT,
    create_time DATETIME DEFAULT GETDATE(),
    update_by NVARCHAR(50),
    update_org_id BIGINT,
    update_time DATETIME DEFAULT GETDATE(),
    remark NVARCHAR(500)
);

-- 添加表注释
EXEC sp_addextendedproperty N'MS_Description', N'菜单表', N'SCHEMA', N'dbo', N'TABLE', N'menu_info';

-- 添加列注释
EXEC sp_addextendedproperty N'MS_Description', N'父菜单ID', N'SCHEMA', N'dbo', N'TABLE', N'menu_info', N'COLUMN', N'parent_id';
EXEC sp_addextendedproperty N'MS_Description', N'菜单名称', N'SCHEMA', N'dbo', N'TABLE', N'menu_info', N'COLUMN', N'menu_name';
EXEC sp_addextendedproperty N'MS_Description', N'菜单标识', N'SCHEMA', N'dbo', N'TABLE', N'menu_info', N'COLUMN', N'menu_key';
EXEC sp_addextendedproperty N'MS_Description', N'菜单URL', N'SCHEMA', N'dbo', N'TABLE', N'menu_info', N'COLUMN', N'menu_url';
EXEC sp_addextendedproperty N'MS_Description', N'菜单图标', N'SCHEMA', N'dbo', N'TABLE', N'menu_info', N'COLUMN', N'menu_icon';
EXEC sp_addextendedproperty N'MS_Description', N'显示顺序', N'SCHEMA', N'dbo', N'TABLE', N'menu_info', N'COLUMN', N'menu_sort';
EXEC sp_addextendedproperty N'MS_Description', N'状态（0：正常，1：停用）', N'SCHEMA', N'dbo', N'TABLE', N'menu_info', N'COLUMN', N'status';
EXEC sp_addextendedproperty N'MS_Description', N'删除标志（0：存在，1：删除）', N'SCHEMA', N'dbo', N'TABLE', N'menu_info', N'COLUMN', N'del_flag';
EXEC sp_addextendedproperty N'MS_Description', N'创建者', N'SCHEMA', N'dbo', N'TABLE', N'menu_info', N'COLUMN', N'create_by';
EXEC sp_addextendedproperty N'MS_Description', N'创建者机构ID', N'SCHEMA', N'dbo', N'TABLE', N'menu_info', N'COLUMN', N'create_org_id';
EXEC sp_addextendedproperty N'MS_Description', N'创建时间', N'SCHEMA', N'dbo', N'TABLE', N'menu_info', N'COLUMN', N'create_time';
EXEC sp_addextendedproperty N'MS_Description', N'更新者', N'SCHEMA', N'dbo', N'TABLE', N'menu_info', N'COLUMN', N'update_by';
EXEC sp_addextendedproperty N'MS_Description', N'更新者机构ID', N'SCHEMA', N'dbo', N'TABLE', N'menu_info', N'COLUMN', N'update_org_id';
EXEC sp_addextendedproperty N'MS_Description', N'更新时间', N'SCHEMA', N'dbo', N'TABLE', N'menu_info', N'COLUMN', N'update_time';
EXEC sp_addextendedproperty N'MS_Description', N'备注', N'SCHEMA', N'dbo', N'TABLE', N'menu_info', N'COLUMN', N'remark';

-- 创建用户角色关联表
CREATE TABLE user_role (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    create_by NVARCHAR(50),
    create_org_id BIGINT,
    create_time DATETIME DEFAULT GETDATE(),
    PRIMARY KEY (user_id, role_id)
);

-- 添加表注释
EXEC sp_addextendedproperty N'MS_Description', N'用户角色关联表', N'SCHEMA', N'dbo', N'TABLE', N'user_role';

-- 添加列注释
EXEC sp_addextendedproperty N'MS_Description', N'用户ID', N'SCHEMA', N'dbo', N'TABLE', N'user_role', N'COLUMN', N'user_id';
EXEC sp_addextendedproperty N'MS_Description', N'角色ID', N'SCHEMA', N'dbo', N'TABLE', N'user_role', N'COLUMN', N'role_id';
EXEC sp_addextendedproperty N'MS_Description', N'创建者', N'SCHEMA', N'dbo', N'TABLE', N'user_role', N'COLUMN', N'create_by';
EXEC sp_addextendedproperty N'MS_Description', N'创建者机构ID', N'SCHEMA', N'dbo', N'TABLE', N'user_role', N'COLUMN', N'create_org_id';
EXEC sp_addextendedproperty N'MS_Description', N'创建时间', N'SCHEMA', N'dbo', N'TABLE', N'user_role', N'COLUMN', N'create_time';

-- 创建角色权限关联表
CREATE TABLE role_permission (
    role_id BIGINT NOT NULL,
    permission_id BIGINT NOT NULL,
    create_by NVARCHAR(50),
    create_org_id BIGINT,
    create_time DATETIME DEFAULT GETDATE(),
    PRIMARY KEY (role_id, permission_id)
);

-- 添加表注释
EXEC sp_addextendedproperty N'MS_Description', N'角色权限关联表', N'SCHEMA', N'dbo', N'TABLE', N'role_permission';

-- 添加列注释
EXEC sp_addextendedproperty N'MS_Description', N'角色ID', N'SCHEMA', N'dbo', N'TABLE', N'role_permission', N'COLUMN', N'role_id';
EXEC sp_addextendedproperty N'MS_Description', N'权限ID', N'SCHEMA', N'dbo', N'TABLE', N'role_permission', N'COLUMN', N'permission_id';
EXEC sp_addextendedproperty N'MS_Description', N'创建者', N'SCHEMA', N'dbo', N'TABLE', N'role_permission', N'COLUMN', N'create_by';
EXEC sp_addextendedproperty N'MS_Description', N'创建者机构ID', N'SCHEMA', N'dbo', N'TABLE', N'role_permission', N'COLUMN', N'create_org_id';
EXEC sp_addextendedproperty N'MS_Description', N'创建时间', N'SCHEMA', N'dbo', N'TABLE', N'role_permission', N'COLUMN', N'create_time';

-- 创建角色菜单关联表
CREATE TABLE role_menu (
    role_id BIGINT NOT NULL,
    menu_id BIGINT NOT NULL,
    create_by NVARCHAR(50),
    create_org_id BIGINT,
    create_time DATETIME DEFAULT GETDATE(),
    PRIMARY KEY (role_id, menu_id)
);

-- 添加表注释
EXEC sp_addextendedproperty N'MS_Description', N'角色菜单关联表', N'SCHEMA', N'dbo', N'TABLE', N'role_menu';

-- 添加列注释
EXEC sp_addextendedproperty N'MS_Description', N'角色ID', N'SCHEMA', N'dbo', N'TABLE', N'role_menu', N'COLUMN', N'role_id';
EXEC sp_addextendedproperty N'MS_Description', N'菜单ID', N'SCHEMA', N'dbo', N'TABLE', N'role_menu', N'COLUMN', N'menu_id';
EXEC sp_addextendedproperty N'MS_Description', N'创建者', N'SCHEMA', N'dbo', N'TABLE', N'role_menu', N'COLUMN', N'create_by';
EXEC sp_addextendedproperty N'MS_Description', N'创建者机构ID', N'SCHEMA', N'dbo', N'TABLE', N'role_menu', N'COLUMN', N'create_org_id';
EXEC sp_addextendedproperty N'MS_Description', N'创建时间', N'SCHEMA', N'dbo', N'TABLE', N'role_menu', N'COLUMN', N'create_time';

-- 创建索引
CREATE INDEX idx_user_info_login_id ON user_info(login_id);
CREATE INDEX idx_user_info_org_id ON user_info(org_id);
CREATE INDEX idx_role_info_role_key ON role_info(role_key);
CREATE INDEX idx_permission_info_permission_key ON permission_info(permission_key);
CREATE INDEX idx_menu_info_parent_id ON menu_info(parent_id);
CREATE INDEX idx_menu_info_menu_key ON menu_info(menu_key);
CREATE INDEX idx_org_info_org_code ON org_info(org_code);
CREATE INDEX idx_org_info_parent_id ON org_info(parent_id);
CREATE INDEX idx_org_info_org_type ON org_info(org_type); 