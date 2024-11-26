package org.nott.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.nott.dto.OrderItemDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Nott
 * @date 2024-10-8
 */
@ApiModel("支付订单完成Vo")
@Data
public class PayOrderVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("结算号-商家叫号")
    private String orderNo;

    @ApiModelProperty("内部订单id")
    private Long payOrderId;

    @ApiModelProperty("结算时间")
    private Date settleTime;

    @ApiModelProperty("门店id")
    private Long shopId;

    @ApiModelProperty("门店名称")
    private String shopName;

    @ApiModelProperty("门店地址")
    private String shopAddress;

    @ApiModelProperty("订单商品内容")
    private List<OrderItemVo> itemInfo;

    @ApiModelProperty("原价")
    private BigDecimal originAmount;

    @ApiModelProperty("实付")
    private BigDecimal totalAmount;

    @ApiModelProperty("本单餐品总等待时间")
    private Long waitTime;
}
