package org.nott.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
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
    private Integer orderWay;

    @ApiModelProperty("订购商品列表")
    private List<OrderItemDTO> items;
}
