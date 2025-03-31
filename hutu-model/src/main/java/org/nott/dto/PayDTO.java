package org.nott.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Nott
 * @date 2025-3-28
 */

@Data
@ApiModel("支付DTO")
public class PayDTO {

    @ApiModelProperty("支付订单号")
    private String payNo;

    @ApiModelProperty("支付订单id")
    private String payOrderId;

    @ApiModelProperty("支付方式")
    private String payCode;

    @ApiModelProperty("额外信息")
    private String extra;

}
