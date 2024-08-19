package org.nott.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.nott.model.BizCouponCondition;

import java.math.BigDecimal;

/**
 * @author Nott
 * @date 2024-8-19
 */

@Data
@ApiModel("用户优惠券VO")
public class UserCouponVo {

    @ApiModelProperty("优惠券id")
    private Long id;

    @ApiModelProperty("主标题")
    private String titile;

    @ApiModelProperty("折扣金额")
    private BigDecimal discountFee;

    @ApiModelProperty("二级标题")
    private String subTitle;

    @ApiModelProperty("当前是否可用")
    private boolean isUsable;

    @ApiModelProperty("使用条件")
    private BizCouponCondition condition;

}
