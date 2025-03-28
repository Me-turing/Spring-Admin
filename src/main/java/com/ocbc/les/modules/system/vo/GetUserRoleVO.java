package com.ocbc.les.modules.system.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "用户权限列表")
public class GetUserRoleVO {

    @Schema(description = "权限列表")
    private List<UserRoleInfo> userRoleInfos;

    @Data
    public static class UserRoleInfo{

        /**
         * 角色ID
         */
        private String roleId;

        /**
         * 角色编码
         */
        private String roleCode;

        /**
         * 角色中文名称
         */
        private String roleNameZh;

        /**
         * 角色英文名称
         */
        private String roleNameEn;

        /**
         * 显示顺序
         */
        private String roleSort;

        /**
         * 备注
         */
        private String remark;
    }
}
