package org.nott.vo.pay;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Nott
 * @date 2025-3-31
 */
@Data
@ApiModel("查询结果Vo")
public class QueryResultVo extends BaseResultVo {

    @ApiModelProperty("支付订单编号")
    private String orderNo;

    @ApiModelProperty("退款订单编号")
    private String refundOrderNo;

    @ApiModelProperty("支付方式")
    private String payCode;

    @ApiModelProperty("支付金额")
    private BigDecimal totalAmount;

    @ApiModelProperty("支付状态")
    private Integer status;

    @ApiModelProperty("退款状态")
    private Integer refundStatus;

    @ApiModelProperty("附加信息")
    private String extra;
}
