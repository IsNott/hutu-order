package org.nott.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Nott
 * @date 2024-8-8
 */

@ApiModel("查询支付方式DTO")
@Data
public class PayWayQueryDTO {

    @ApiModelProperty(value = "当前平台",required = true)
    @NotNull(message = "平台为空")
    private String platformName;
}
