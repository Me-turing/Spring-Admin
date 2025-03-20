-- 开启事务
BEGIN TRY
    BEGIN TRANSACTION;

    -- 使用MERGE语句插入机构数据
    MERGE org_info AS target
    USING (VALUES 
        (N'HQ', N'OCBC总部', N'OCBC Headquarters', NULL, N'1', 1, N'0', N'admin', 1, N'OCBC总部机构'),
        (N'CN', N'中国分行', N'OCBC China', 1, N'2', 1, N'0', N'admin', 1, N'OCBC中国分行'),
        (N'CN-SH', N'上海支行', N'OCBC Shanghai', 2, N'3', 1, N'0', N'admin', 1, N'OCBC上海支行'),
        (N'CN-SH-RM', N'风险管理部', N'Risk Management', 3, N'4', 1, N'0', N'admin', 1, N'上海支行风险管理部')
    ) AS source (org_code, org_name_zh, org_name_en, parent_id, org_type, org_sort, status, create_by, create_org_id, remark)
    ON target.org_code = source.org_code
    WHEN NOT MATCHED THEN
        INSERT (org_code, org_name_zh, org_name_en, parent_id, org_type, org_sort, status, create_by, create_org_id, remark)
        VALUES (source.org_code, source.org_name_zh, source.org_name_en, source.parent_id, source.org_type, source.org_sort, source.status, source.create_by, source.create_org_id, source.remark);

    -- 使用MERGE语句插入用户数据
    MERGE user_info AS target
    USING (VALUES 
        (N'admin', N'$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', N'系统管理员', N'System Administrator', 4, N'0', N'admin', 4, N'超级管理员')
    ) AS source (login_id, password, user_name_zh, user_name_en, org_id, status, create_by, create_org_id, remark)
    ON target.login_id = source.login_id
    WHEN NOT MATCHED THEN
        INSERT (login_id, password, user_name_zh, user_name_en, org_id, status, create_by, create_org_id, remark)
        VALUES (source.login_id, source.password, source.user_name_zh, source.user_name_en, source.org_id, source.status, source.create_by, source.create_org_id, source.remark);

    -- 使用MERGE语句插入角色数据
    MERGE role_info AS target
    USING (VALUES 
        (N'超级管理员', N'admin', 1, N'0', N'admin', 4, N'超级管理员角色'),
        (N'系统管理员', N'system', 2, N'0', N'admin', 4, N'系统管理员角色'),
        (N'普通用户', N'user', 3, N'0', N'admin', 4, N'普通用户角色')
    ) AS source (role_name, role_key, role_sort, status, create_by, create_org_id, remark)
    ON target.role_key = source.role_key
    WHEN NOT MATCHED THEN
        INSERT (role_name, role_key, role_sort, status, create_by, create_org_id, remark)
        VALUES (source.role_name, source.role_key, source.role_sort, source.status, source.create_by, source.create_org_id, source.remark);

    -- 使用MERGE语句插入菜单数据
    MERGE menu_info AS target
    USING (VALUES 
        (0, N'系统管理', N'system', N'/system', N'system', 1, N'0', N'admin', 4, N'系统管理菜单'),
        (1, N'用户管理', N'system:user', N'/system/user', N'user', 1, N'0', N'admin', 4, N'用户管理菜单'),
        (1, N'角色管理', N'system:role', N'/system/role', N'role', 2, N'0', N'admin', 4, N'角色管理菜单'),
        (1, N'菜单管理', N'system:menu', N'/system/menu', N'menu', 3, N'0', N'admin', 4, N'菜单管理菜单'),
        (1, N'机构管理', N'system:org', N'/system/org', N'org', 4, N'0', N'admin', 4, N'机构管理菜单')
    ) AS source (parent_id, menu_name, menu_key, menu_url, menu_icon, menu_sort, status, create_by, create_org_id, remark)
    ON target.menu_key = source.menu_key
    WHEN NOT MATCHED THEN
        INSERT (parent_id, menu_name, menu_key, menu_url, menu_icon, menu_sort, status, create_by, create_org_id, remark)
        VALUES (source.parent_id, source.menu_name, source.menu_key, source.menu_url, source.menu_icon, source.menu_sort, source.status, source.create_by, source.create_org_id, source.remark);

    -- 使用MERGE语句插入权限数据
    MERGE permission_info AS target
    USING (VALUES 
        (N'用户查询', N'system:user:query', 1, N'0', N'admin', 4, N'用户查询权限'),
        (N'用户新增', N'system:user:add', 2, N'0', N'admin', 4, N'用户新增权限'),
        (N'用户修改', N'system:user:edit', 3, N'0', N'admin', 4, N'用户修改权限'),
        (N'用户删除', N'system:user:delete', 4, N'0', N'admin', 4, N'用户删除权限'),
        (N'用户导出', N'system:user:export', 5, N'0', N'admin', 4, N'用户导出权限'),
        (N'用户导入', N'system:user:import', 6, N'0', N'admin', 4, N'用户导入权限'),
        (N'重置密码', N'system:user:resetPwd', 7, N'0', N'admin', 4, N'重置密码权限'),
        (N'角色查询', N'system:role:query', 8, N'0', N'admin', 4, N'角色查询权限'),
        (N'角色新增', N'system:role:add', 9, N'0', N'admin', 4, N'角色新增权限'),
        (N'角色修改', N'system:role:edit', 10, N'0', N'admin', 4, N'角色修改权限'),
        (N'角色删除', N'system:role:delete', 11, N'0', N'admin', 4, N'角色删除权限'),
        (N'菜单查询', N'system:menu:query', 12, N'0', N'admin', 4, N'菜单查询权限'),
        (N'菜单新增', N'system:menu:add', 13, N'0', N'admin', 4, N'菜单新增权限'),
        (N'菜单修改', N'system:menu:edit', 14, N'0', N'admin', 4, N'菜单修改权限'),
        (N'菜单删除', N'system:menu:delete', 15, N'0', N'admin', 4, N'菜单删除权限'),
        (N'机构查询', N'system:org:query', 16, N'0', N'admin', 4, N'机构查询权限'),
        (N'机构新增', N'system:org:add', 17, N'0', N'admin', 4, N'机构新增权限'),
        (N'机构修改', N'system:org:edit', 18, N'0', N'admin', 4, N'机构修改权限'),
        (N'机构删除', N'system:org:delete', 19, N'0', N'admin', 4, N'机构删除权限')
    ) AS source (permission_name, permission_key, permission_sort, status, create_by, create_org_id, remark)
    ON target.permission_key = source.permission_key
    WHEN NOT MATCHED THEN
        INSERT (permission_name, permission_key, permission_sort, status, create_by, create_org_id, remark)
        VALUES (source.permission_name, source.permission_key, source.permission_sort, source.status, source.create_by, source.create_org_id, source.remark);

    -- 使用MERGE语句插入用户角色关联数据
    MERGE user_role AS target
    USING (VALUES 
        (1, 1, N'admin', 4)
    ) AS source (user_id, role_id, create_by, create_org_id)
    ON target.user_id = source.user_id AND target.role_id = source.role_id
    WHEN NOT MATCHED THEN
        INSERT (user_id, role_id, create_by, create_org_id)
        VALUES (source.user_id, source.role_id, source.create_by, source.create_org_id);

    -- 使用MERGE语句插入角色菜单关联数据
    MERGE role_menu AS target
    USING (
        SELECT 1 as role_id, id as menu_id, N'admin' as create_by, 4 as create_org_id
        FROM menu_info
    ) AS source
    ON target.role_id = source.role_id AND target.menu_id = source.menu_id
    WHEN NOT MATCHED THEN
        INSERT (role_id, menu_id, create_by, create_org_id)
        VALUES (source.role_id, source.menu_id, source.create_by, source.create_org_id);

    -- 使用MERGE语句插入角色权限关联数据
    MERGE role_permission AS target
    USING (
        SELECT 1 as role_id, id as permission_id, N'admin' as create_by, 4 as create_org_id
        FROM permission_info
    ) AS source
    ON target.role_id = source.role_id AND target.permission_id = source.permission_id
    WHEN NOT MATCHED THEN
        INSERT (role_id, permission_id, create_by, create_org_id)
        VALUES (source.role_id, source.permission_id, source.create_by, source.create_org_id);

    -- 提交事务
    COMMIT TRANSACTION;
END TRY
BEGIN CATCH
    -- 回滚事务
    IF @@TRANCOUNT > 0
        ROLLBACK TRANSACTION;
    
    -- 抛出错误
    DECLARE @ErrorMessage NVARCHAR(4000) = ERROR_MESSAGE();
    DECLARE @ErrorSeverity INT = ERROR_SEVERITY();
    DECLARE @ErrorState INT = ERROR_STATE();

    RAISERROR (@ErrorMessage, @ErrorSeverity, @ErrorState);
END CATCH; 