package org.nott.vo;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Nott
 * @date 2024-12-11
 */
@Data
@ApiModel("我的订单对象")
public class MyPayOrderVo {

    @ApiModelProperty("订单商户地址")
    private String address;

    @ApiModelProperty("订单商户名称")
    private String shopName;

    @ApiModelProperty("商品列表")
    private List<OrderItemVo> items;

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("0-单点 1-套餐")
    private Integer orderType;

    @ApiModelProperty("0-堂食 1-打包 2-外送")
    private Integer pickType;

    @ApiModelProperty("总金额")
    private BigDecimal totalAmount;

    @ApiModelProperty("原金额")
    private BigDecimal originAmount;

    @ApiModelProperty("菜品信息")
    @JsonIgnore
    private String itemInfo;

    @ApiModelProperty("菜品统计")
    private Integer itemPiece;

    @ApiModelProperty("预计等待时间")
    private Integer waitTime;

    @ApiModelProperty("0-初始化 1-进行中 2-支付 3-退单")
    private Integer orderStatus;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("支付时间")
    private Date settleTime;

    @ApiModelProperty("店铺id")
    private Long shopId;

}
