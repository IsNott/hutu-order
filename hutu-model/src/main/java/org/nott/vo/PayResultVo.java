package org.nott.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Nott
 * @date 2025-3-28
 */

@Data
@ApiModel("支付结果Vo")
public class PayResultVo {
    @ApiModelProperty("支付订单号")
    private String payNo;
    @ApiModelProperty("支付订单id")
    private String payOrderId;
    @ApiModelProperty("外部订单号")
    private String outTradeNo;

}
