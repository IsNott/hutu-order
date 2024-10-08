package org.nott.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author Nott
 * @date 2024-10-8
 */
@ApiModel("用户结算Vo")
@Data
public class SettleOrderVo {

    @ApiModelProperty("应付总金额")
    private String totalAmount;

    @ApiModelProperty("订单表id")
    private Long orderId;

    @ApiModelProperty("创建时间")
    private Date createTime;
}
