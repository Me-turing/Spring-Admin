package com.ocbc.les.modules.system.mapper;

import com.ocbc.les.modules.system.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 用户信息Mapper接口
 */
@Mapper
public interface UserInfoMapper {
    
    /**
     * 根据登录ID查询用户信息
     *
     * @param loginId 登录ID
     * @return 用户信息
     */
    @Select("SELECT * FROM user_info WHERE login_id = #{loginId} AND del_flag = '0'")
    UserInfo selectByLoginId(@Param("loginId") String loginId);
    
    /**
     * 根据用户ID查询用户信息
     *
     * @param id 用户ID
     * @return 用户信息
     */
    @Select("SELECT * FROM user_info WHERE id = #{id} AND del_flag = '0'")
    UserInfo selectById(@Param("id") Long id);

    /**
     * 更新用户信息
     *
     * @param userInfo 用户信息
     */
    @Update("UPDATE user_info SET last_login_time = #{lastLoginTime}, update_time = GETDATE() WHERE id = #{id}")
    void updateById(UserInfo userInfo);
} 