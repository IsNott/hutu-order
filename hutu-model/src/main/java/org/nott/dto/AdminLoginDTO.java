package org.nott.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Nott
 * @date 2024-6-6
 */
@ApiModel("后台登录-DTO")
@Data
public class AdminLoginDTO {

    @ApiModelProperty("用户名")
    @NotNull(message = "用户名不能为空")
    private String username;

    @ApiModelProperty("密码")
    @NotNull(message = "密码不能为空")
    private String password;
}
