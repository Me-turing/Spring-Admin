package com.ocbc.les.frame.security.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * Token响应DTO
 */
@Data
@Builder
@Schema(description = "Token响应DTO")
public class TokenDTO {
    
    @Schema(description = "访问令牌")
    private String accessToken;

    @Schema(description = "访问令牌过期时间(秒)")
    private Long accessTokenExpireIn;

} 