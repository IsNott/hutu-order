package org.nott.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.nott.dto.OrderItemDTO;
import org.nott.model.BizPayOrder;

import java.util.List;

/**
 * @author Nott
 * @date 2024-12-11
 */
@Data
@ApiModel("我的订单对象")
public class MyPayOrderVo extends BizPayOrder {

    @ApiModelProperty("订单商户地址")
    private String address;

    @ApiModelProperty("订单商户名称")
    private String shopName;

    @ApiModelProperty("商品列表")
    private List<OrderItemVo> items;
}
