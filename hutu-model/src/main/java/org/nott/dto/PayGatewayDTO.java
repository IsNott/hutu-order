package org.nott.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

/**
 * @author Nott
 * @date 2025-3-31
 */

@Data
@ApiModel("支付网关DTO")
public class PayGatewayDTO {

    @ApiModelProperty("订单号")
    private String orderNo;

    @ApiModelProperty("订单id")
    private String orderId;

    @NotBlank(message = "业务编码不能为空")
    @ApiModelProperty("业务编码")
    private String businessCode;

    @ApiModelProperty("支付方式")
    private String payCode;

    @ApiModelProperty("是否全额退款")
    private boolean isFull;

    @ApiModelProperty("退款金额")
    private BigDecimal refundAmount;

    @ApiModelProperty("退款原因")
    private String refundReason;

}
