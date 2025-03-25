package com.ocbc.les.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ocbc.les.modules.system.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 用户信息Mapper接口
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
    
    /**
     * 根据登录ID查询用户信息
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    @Select("SELECT * FROM user_info WHERE user_id = #{userId}")
    UserInfo selectById(@Param("userId") String userId);

    /**
     * 更新用户信息
     *
     * @param userInfo 用户信息
     * @return
     */
    @Update("UPDATE user_info SET last_login_time = #{lastLoginTime}, update_time = GETDATE() WHERE id = #{id}")
    int updateById(UserInfo userInfo);

    /**
     * 新增用户
     *
     * @param userInfo 用户信息
     */
    void addUser(UserInfo userInfo);
} 