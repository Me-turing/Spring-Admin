package com.ocbc.les.frame.cache.entity;

import lombok.Data;

import java.util.Date;

@Data
public class JwtCacheInfo {
    /**
     * 当前Token
     */
    private String token;

    /**
     * 最后活跃时间
     */
    private Date lastActiveTime;
}
