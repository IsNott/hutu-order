package org.nott.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author Nott
 * @date 2024-10-8
 */
@ApiModel("订单商品DTO")
@Data
public class OrderItemDTO {
    @ApiModelProperty("购物袋记录id")
    private Long id;

    @ApiModelProperty(value = "订购商品id", required = true)
    @NotNull
    private Long itemId;

    @ApiModelProperty("商品图片")
    private String itemImageUrls;

    @ApiModelProperty("商品名称")
    private String itemName;

    @ApiModelProperty(value = "订购商品sku正文", required = true)
    private String skuItemContents;

    @ApiModelProperty(value = "订购商品数量", required = true)
    @NotNull
    private Integer itemPiece;

    @ApiModelProperty("商品单价")
    private BigDecimal singleActuallyAmount;

    @ApiModelProperty("预计制作时间")
    private Integer expectMakeTime;
}
