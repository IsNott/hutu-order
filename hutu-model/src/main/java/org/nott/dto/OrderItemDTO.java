package org.nott.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Nott
 * @date 2024-10-8
 */
@ApiModel("订单商品DTO")
@Data
public class OrderItemDTO {

    @ApiModelProperty(value = "订购商品id", required = true)
    private Long itemId;

    @ApiModelProperty(value = "订购商品sku正文", required = true)
    private String skuItemContents;

    @ApiModelProperty(value = "订购商品数量", required = true)
    private Integer itemPiece;
}
