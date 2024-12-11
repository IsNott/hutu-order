package org.nott.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Nott
 * @date 2024-12-11
 */
@Data
@ApiModel("查询我的订单DTO")
public class MyOrderQueryDTO {

    @ApiModelProperty("状态")
    private Integer status;

}
