package org.nott.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Nott
 * @date 2024-6-17
 */

@Data
public class SysUserInfoDTO {

    @NotNull(message = "用户名称不能为空")
    private String username;

    @NotNull(message = "密码不能为空")
    private String password;

    private String avatarUrl;
}
