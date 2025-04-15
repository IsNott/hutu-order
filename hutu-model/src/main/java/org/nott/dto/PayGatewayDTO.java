package org.nott.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Nott
 * @date 2025-3-31
 */

@Data
@ApiModel("支付网关DTO")
public class PayGatewayDTO {

    @ApiModelProperty("订单号")
    private String orderNo;

    @NotBlank(message = "业务编码不能为空")
    @ApiModelProperty("业务编码")
    private String businessCode;

    @ApiModelProperty("支付方式")
    private String payCode;

    @ApiModelProperty("退款dto")
    private RefundDTO refundDTO;

    @ApiModelProperty("支付dto")
    private PayDTO payDTO;


}
