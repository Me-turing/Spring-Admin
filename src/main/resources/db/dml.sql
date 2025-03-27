-- 初始化管理员
INSERT INTO demo.dbo.user_info (user_id, login_id, password, user_name_zh, user_name_en, status, del_flag, create_by, create_org_id, create_time, update_by, update_org_id, update_time, remark)
VALUES (N'admin', N'admin', N'5ac537f82817f1d478fccc441857ef25', N'系统管理员', N'System Administrator', N'0', N'0', N'System', N'000000', N'2025-03-27 16:27:59.000', N'System', N'000000', N'2025-03-27 16:28:14.000', null);

-- 初始化操作类型代码
INSERT INTO code_library (code_id, code_type, code_value, code_name_zh, code_name_en, code_desc, code_sort, status, del_flag, create_by, create_org_id, create_time, update_by, update_org_id, update_time, remark)
VALUES 
-- 基础操作
('CODE001', 'OPERATE_TYPE', 'view', '查看', 'View', '查看数据权限', '1', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('CODE002', 'OPERATE_TYPE', 'add', '新增', 'Add', '新增数据权限', '2', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('CODE003', 'OPERATE_TYPE', 'edit', '编辑', 'Edit', '编辑数据权限', '3', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('CODE004', 'OPERATE_TYPE', 'delete', '删除', 'Delete', '删除数据权限', '4', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
-- 审批操作
('CODE005', 'OPERATE_TYPE', 'approve', '审批', 'Approve', '审批数据权限', '5', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('CODE006', 'OPERATE_TYPE', 'reject', '拒绝', 'Reject', '拒绝数据权限', '6', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
-- 导入导出
('CODE007', 'OPERATE_TYPE', 'export', '导出', 'Export', '导出数据权限', '7', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('CODE008', 'OPERATE_TYPE', 'import', '导入', 'Import', '导入数据权限', '8', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
-- 文件操作
('CODE009', 'OPERATE_TYPE', 'print', '打印', 'Print', '打印数据权限', '9', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('CODE010', 'OPERATE_TYPE', 'download', '下载', 'Download', '下载数据权限', '10', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('CODE011', 'OPERATE_TYPE', 'upload', '上传', 'Upload', '上传数据权限', '11', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
-- 其他操作
('CODE012', 'OPERATE_TYPE', 'search', '搜索', 'Search', '搜索数据权限', '12', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('CODE013', 'OPERATE_TYPE', 'reset', '重置', 'Reset', '重置数据权限', '13', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('CODE014', 'OPERATE_TYPE', 'lock', '锁定', 'Lock', '锁定数据权限', '14', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('CODE015', 'OPERATE_TYPE', 'unlock', '解锁', 'Unlock', '解锁数据权限', '15', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('CODE016', 'OPERATE_TYPE', 'enable', '启用', 'Enable', '启用数据权限', '16', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('CODE017', 'OPERATE_TYPE', 'disable', '禁用', 'Disable', '禁用数据权限', '17', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null);

-- 初始化机构数据
INSERT INTO org_info (org_id, org_key, org_name_zh, org_name_en, parent_id, org_type, org_sort, status, del_flag, create_by, create_org_id, create_time, update_by, update_org_id, update_time, remark)
VALUES 
-- 总部
('000000', 'HQ', '浙江总部', 'Zhejiang Headquarters', null, '1', '1', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
-- 分部
('100000', 'HZ', '杭州分部', 'Hangzhou Branch', '000000', '2', '1', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('200000', 'NB', '宁波分部', 'Ningbo Branch', '000000', '2', '2', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
-- 支部
('110000', 'XH', '西湖支部', 'Xihu Branch', '100000', '3', '1', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('120000', 'BJ', '滨江支部', 'Binjiang Branch', '100000', '3', '2', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('210000', 'HS', '海曙支部', 'Haishu Branch', '200000', '3', '1', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
-- 组
('111000', 'TECH', '技术组', 'Technology Group', '110000', '4', '1', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('112000', 'BIZ', '业务组', 'Business Group', '110000', '4', '2', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('113000', 'RISK', '风控组', 'Risk Control Group', '110000', '4', '3', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null);

-- 初始化角色数据
INSERT INTO role_info (role_id, role_code, role_name_zh, role_name_en, role_sort, status, del_flag, create_by, create_org_id, create_time, update_by, update_org_id, update_time, remark)
VALUES 
('ROLE001', 'SYSTEM_ADMIN', '系统管理员', 'System Administrator', '1', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('ROLE002', 'BRANCH_ADMIN', '分部管理员', 'Branch Administrator', '2', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('ROLE003', 'DEPARTMENT_ADMIN', '支部管理员', 'Department Administrator', '3', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('ROLE004', 'NORMAL_USER', '普通用户', 'Normal User', '4', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null);

-- 初始化用户数据
INSERT INTO user_info (user_id, login_id, password, user_name_zh, user_name_en, status, del_flag, create_by, create_org_id, create_time, update_by, update_org_id, update_time, remark)
VALUES 
('USER001', 'hzadmin', N'5ac537f82817f1d478fccc441857ef25', '杭州管理员', 'Hangzhou Administrator', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('USER002', 'nbadmin', N'5ac537f82817f1d478fccc441857ef25', '宁波管理员', 'Ningbo Administrator', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('USER003', 'xhadmin', N'5ac537f82817f1d478fccc441857ef25', '西湖管理员', 'Xihu Administrator', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('USER004', 'bjadmin', N'5ac537f82817f1d478fccc441857ef25', '滨江管理员', 'Binjiang Administrator', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('USER005', 'hsadmin', N'5ac537f82817f1d478fccc441857ef25', '海曙管理员', 'Haishu Administrator', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('USER006', 'tech001', N'5ac537f82817f1d478fccc441857ef25', '技术员1', 'Technician 1', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('USER007', 'biz001', N'5ac537f82817f1d478fccc441857ef25', '业务员1', 'Business Staff 1', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('USER008', 'risk001', N'5ac537f82817f1d478fccc441857ef25', '风控员1', 'Risk Controller 1', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null);

-- 初始化用户机构关联
INSERT INTO user_org (user_id, org_id, is_primary, status, del_flag, create_by, create_org_id, create_time, update_by, update_org_id, update_time, remark)
VALUES 
-- 杭州管理员
('USER001', '100000', '1', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
-- 宁波管理员
('USER002', '200000', '1', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
-- 西湖管理员
('USER003', '110000', '1', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
-- 滨江管理员
('USER004', '120000', '1', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
-- 海曙管理员
('USER005', '210000', '1', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
-- 技术员
('USER006', '111000', '1', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
-- 业务员
('USER007', '112000', '1', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
-- 风控员
('USER008', '113000', '1', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null);

-- 初始化用户角色关联
INSERT INTO user_role (user_id, role_id, status, del_flag, create_by, create_org_id, create_time, update_by, update_org_id, update_time, remark)
VALUES 
-- 系统管理员
('admin', 'ROLE001', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
-- 杭州管理员
('USER001', 'ROLE002', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
-- 宁波管理员
('USER002', 'ROLE002', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
-- 西湖管理员
('USER003', 'ROLE003', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
-- 滨江管理员
('USER004', 'ROLE003', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
-- 海曙管理员
('USER005', 'ROLE003', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
-- 普通用户
('USER006', 'ROLE004', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('USER007', 'ROLE004', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('USER008', 'ROLE004', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null);

-- 初始化菜单数据
INSERT INTO menu_info (menu_id, parent_id, menu_name_zh, menu_name_en, menu_path, menu_icon, menu_sort, status, del_flag, create_by, create_org_id, create_time, update_by, update_org_id, update_time, remark)
VALUES 
-- 系统管理
('M001', null, '系统管理', 'System Management', '/system', 'setting', '1', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('M001001', 'M001', '用户管理', 'User Management', '/system/user', 'user', '1', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('M001002', 'M001', '角色管理', 'Role Management', '/system/role', 'team', '2', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('M001003', 'M001', '菜单管理', 'Menu Management', '/system/menu', 'menu', '3', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
-- 业务管理
('M002', null, '业务管理', 'Business Management', '/business', 'shop', '2', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('M002001', 'M002', '业务处理', 'Business Process', '/business/process', 'form', '1', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('M002002', 'M002', '业务查询', 'Business Query', '/business/query', 'search', '2', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null);

-- 初始化菜单角色关联
INSERT INTO menu_role (menu_id, role_id, status, del_flag, create_by, create_org_id, create_time, update_by, update_org_id, update_time, remark)
VALUES 
-- 系统管理员拥有所有菜单权限
('M001', 'ROLE001', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('M001001', 'ROLE001', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('M001002', 'ROLE001', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('M001003', 'ROLE001', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('M002', 'ROLE001', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('M002001', 'ROLE001', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('M002002', 'ROLE001', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
-- 分部管理员拥有业务管理权限
('M002', 'ROLE002', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('M002001', 'ROLE002', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('M002002', 'ROLE002', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
-- 支部管理员拥有业务管理权限
('M002', 'ROLE003', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('M002001', 'ROLE003', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('M002002', 'ROLE003', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
-- 普通用户只有业务查询权限
('M002002', 'ROLE004', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null);

-- 初始化权限数据
INSERT INTO permission_info (permission_id, permission_key, permission_name_zh, permission_name_en, permission_group, permission_sort, status, del_flag, create_by, create_org_id, create_time, update_by, update_org_id, update_time, remark)
VALUES 
-- 系统管理权限
('P001', 'system:user:view', '查看用户', 'View User', 'system', '1', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('P002', 'system:user:add', '新增用户', 'Add User', 'system', '2', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('P003', 'system:user:edit', '编辑用户', 'Edit User', 'system', '3', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('P004', 'system:user:delete', '删除用户', 'Delete User', 'system', '4', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
-- 业务管理权限
('P005', 'business:process:view', '查看业务', 'View Business', 'business', '1', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('P006', 'business:process:add', '新增业务', 'Add Business', 'business', '2', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('P007', 'business:process:edit', '编辑业务', 'Edit Business', 'business', '3', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('P008', 'business:process:delete', '删除业务', 'Delete Business', 'business', '4', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null);

-- 初始化角色权限关联
INSERT INTO role_permission (role_id, permission_id, status, del_flag, create_by, create_org_id, create_time, update_by, update_org_id, update_time, remark)
VALUES 
-- 系统管理员拥有所有权限
('ROLE001', 'P001', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('ROLE001', 'P002', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('ROLE001', 'P003', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('ROLE001', 'P004', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('ROLE001', 'P005', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('ROLE001', 'P006', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('ROLE001', 'P007', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('ROLE001', 'P008', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
-- 分部管理员拥有业务管理权限
('ROLE002', 'P005', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('ROLE002', 'P006', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('ROLE002', 'P007', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('ROLE002', 'P008', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
-- 支部管理员拥有业务管理权限
('ROLE003', 'P005', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('ROLE003', 'P006', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('ROLE003', 'P007', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
('ROLE003', 'P008', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null),
-- 普通用户只有查看权限
('ROLE004', 'P005', '0', '0', 'System', '000000', GETDATE(), 'System', '000000', GETDATE(), null);
