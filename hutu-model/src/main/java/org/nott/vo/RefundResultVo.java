package org.nott.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Nott
 * @date 2025-3-28
 */

@Data
@ApiModel("退款结果Vo")
public class RefundResultVo {

    @ApiModelProperty("订单号")
    private String orderNo;

    @ApiModelProperty("退款订单号")
    private String refundNo;

    @ApiModelProperty("外部退款订单号")
    private String outRefundTradeNo;

}
