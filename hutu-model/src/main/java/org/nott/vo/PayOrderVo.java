package org.nott.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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

    @ApiModelProperty("订单号")
    private String orderNo;

    @ApiModelProperty("结算号-商家叫号")
    private String shopOrderNo;

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

    @ApiModelProperty("订单状态")
    private Integer orderStatus;

    /**
    * 门店菜单分类表 DTO
    */
    @Data
    @ApiModel(value = "SysMenuCatalogVo", description = "门店菜单分类表页面对象")
    public static class SysMenuCatalogVo {

        private Long id;

        @ApiModelProperty(value = "菜单分类名称")
        private String menuCatalogName;

        @ApiModelProperty(value = "关联门店id")
        private Long shopId;

        @ApiModelProperty(value = "关联门店名称")
        private String shopName;

        @ApiModelProperty(value = "排序")
        private Integer showIndex;

        @ApiModelProperty(value = "点单页显示")
        private Boolean showSide;

        @ApiModelProperty(value = "创建时间")
        private Date createTime;

        @ApiModelProperty(value = "更新时间")
        private Date updateTime;

        @ApiModelProperty(value = "删除标识")
        private Boolean delFlag;

    }
}
