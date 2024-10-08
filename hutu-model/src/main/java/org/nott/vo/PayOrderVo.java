package org.nott.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author Nott
 * @date 2024-10-8
 */
@ApiModel("支付订单完成Vo")
@Data
public class PayOrderVo {

    @ApiModelProperty("结算号-商家叫号")
    private String settleNum;

    @ApiModelProperty("内部订单id")
    private Long payOrderId;

    @ApiModelProperty("结算时间")
    private Date settleTime;

    @ApiModelProperty("门店id")
    private Long shopId;

    @ApiModelProperty("门店名称")
    private String shopName;
}
