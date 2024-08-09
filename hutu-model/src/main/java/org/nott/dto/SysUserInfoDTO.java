package org.nott.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Nott
 * @date 2024-6-17
 */

@ApiModel("系统用户DTO")
@Data
public class SysUserInfoDTO {

    @ApiModelProperty(value = "用户名",required = true)
    @NotNull(message = "用户名称不能为空")
    private String username;

    @ApiModelProperty(value = "密码",required = true)
    @NotNull(message = "密码不能为空")
    private String password;

    @ApiModelProperty(value = "头像地址")
    private String avatarUrl;
}
