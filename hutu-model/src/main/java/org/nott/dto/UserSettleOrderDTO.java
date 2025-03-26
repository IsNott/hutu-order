package org.nott.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Nott
 * @date 2024-10-8
 */
@ApiModel("用户订单结算DTO")
@Data
public class UserSettleOrderDTO {

    @ApiModelProperty("是否使用优惠券")
    private boolean isUseCoupon;

    @ApiModelProperty("是否使用积分")
    private boolean isUsePoint;

    @ApiModelProperty("使用积分数量")
    private Long pointCount;

    @ApiModelProperty("使用优惠券id")
    private Long couponId;

    @ApiModelProperty("订单备注")
    private String remark;

    @ApiModelProperty("就餐方式 0-堂食 1-带走 2-自提 3-外卖")
    private Integer pickType;

    @ApiModelProperty("点餐类型 0-单点 1-套餐")
    private Integer orderType;

    @ApiModelProperty("订购商品列表")
    @NotEmpty(message = "商品不能为空")
    private List<OrderItemDTO> items;

    @ApiModelProperty("门店id")
    @NotEmpty(message = "门店不能为空")
    private Long shopId;

    @ApiModelProperty("总金额")
    private BigDecimal totalAmount;

    @ApiModelProperty("原始金额")
    private BigDecimal originAmount;

    @ApiModelProperty("支付方式code")
    private String payCode;

    public boolean isUseCoupon() {
        return couponId != null;
    }

    public boolean isUsePoint() {
        return pointCount != null && pointCount > 0;
    }
}
