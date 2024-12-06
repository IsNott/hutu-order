package org.nott.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Nott
 * @date 2024-12-6
 */
@Data
@ApiModel("顾客前面订单信息对象")
public class FrontOrderVo {


    @ApiModelProperty("订单数")
    private Integer orderCount;

    @ApiModelProperty("菜品数")
    private Integer pieceCount;

    @ApiModelProperty("预计等待时间")
    private Integer waitTime;
}
