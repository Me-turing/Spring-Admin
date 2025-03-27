package com.ocbc.les.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ocbc.les.modules.system.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
* @author 23399
* @description 针对表【user_info(用户表)】的数据库操作Mapper
* @createDate 2025-03-27 16:33:29
* @Entity com.ocbc.les.modules.system.entity.UserInfo
*/
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    /**
     * 根据登录ID查询用户信息
     *
     * @param loginId 用户ID
     * @return 用户信息
     */
    @Select("SELECT * FROM user_info WHERE login_id = #{loginId}")
    UserInfo selectById(@Param("loginId") String loginId);


    /**
     * 新增用户
     *
     * @param userInfo 用户信息
     */
    void addUser(UserInfo userInfo);
}




