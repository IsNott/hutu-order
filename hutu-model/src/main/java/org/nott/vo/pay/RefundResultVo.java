package org.nott.vo.pay;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Nott
 * @date 2025-3-28
 */

@Data
@ApiModel("退款结果Vo")
public class RefundResultVo extends BaseResultVo {

    @ApiModelProperty("退款订单号")
    private String refundNo;

    @ApiModelProperty("外部退款订单号")
    private String outRefundTradeNo;

    @ApiModelProperty("退款金额")
    private BigDecimal refundAmount;

    @ApiModelProperty("退款状态")
    private Integer refundStatus;

}
