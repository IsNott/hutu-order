package org.nott.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Nott
 * @date 2025-3-28
 */

@Data
@ApiModel("退款DTO")
public class RefundDTO {

    @ApiModelProperty("支付订单号")
    private String payNo;

    @ApiModelProperty("是否全额退款")
    private boolean isFull;

    @ApiModelProperty("退款金额")
    private BigDecimal refundAmount;

    @ApiModelProperty("退款原因")
    private String refundReason;

    @ApiModelProperty("额外信息")
    private String extra;
}
