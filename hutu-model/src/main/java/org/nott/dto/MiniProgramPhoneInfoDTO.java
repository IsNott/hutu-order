package org.nott.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author Nott
 * @date 2024-7-9
 */
@Data
@ApiModel("uni-app小程序登录DTO")
public class MiniProgramPhoneInfoDTO {

    @ApiModelProperty(value = "微信code",required = true)
    @NotEmpty(message = "code不能为空")
    private String code;

    @ApiModelProperty(value = "加密数据",required = true)
    @NotEmpty(message = "encryptedData不能为空")
    private String encryptedData;

    @ApiModelProperty(value = "iv")
    @NotEmpty(message = "iv不能为空")
    private String iv;
}
