
-- 初始化管理员
INSERT INTO demo.dbo.user_info (user_id, login_id, password, user_name_zh, user_name_en, status, del_flag, create_by, create_org_id, create_time, update_by, update_org_id, update_time, remark)
VALUES (N'admin', N'admin', N'5ac537f82817f1d478fccc441857ef25', N'系统管理员', N'System Administrator', N'0', N'0', N'System', N'000000', N'2025-03-27 16:27:59.000', N'System', N'000000', N'2025-03-27 16:28:14.000', null);
