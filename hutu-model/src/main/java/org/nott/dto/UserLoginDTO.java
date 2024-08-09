package org.nott.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;


/**
 * @author Nott
 * @date 2024-7-6
 */
@Data
@ApiModel("用户登录DTO")
public class UserLoginDTO {

    @ApiModelProperty(value = "手机号",required = true)
    @NotEmpty(message = "必填元素不能为空")
    private String phone;
}
