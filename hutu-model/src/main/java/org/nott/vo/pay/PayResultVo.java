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
@ApiModel("支付结果Vo")
public class PayResultVo extends BaseResultVo {

    @ApiModelProperty("支付订单id")
    private String payOrderId;
    @ApiModelProperty("外部订单号")
    private String outTradeNo;
    @ApiModelProperty("支付方式")
    private String payCode;
    @ApiModelProperty("支付金额")
    private BigDecimal payAmount;
}
