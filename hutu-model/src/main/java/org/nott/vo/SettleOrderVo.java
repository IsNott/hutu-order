package org.nott.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Nott
 * @date 2024-10-8
 */
@ApiModel("用户结算Vo")
@Data
public class SettleOrderVo {

    @ApiModelProperty("应付总金额")
    private BigDecimal totalAmount;

    @ApiModelProperty("订单表id")
    private Long orderId;

    @ApiModelProperty("内部订单号")
    private String orderNo;

    @ApiModelProperty("店内用餐号")
    private String shopOrderNo;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("过期时间")
    private Long expireTime;

    @ApiModelProperty("用户id")
    private Long userId;
}
