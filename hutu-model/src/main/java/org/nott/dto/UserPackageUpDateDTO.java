package org.nott.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Nott
 * @date 2024-11-18
 */
@Data
@ApiModel("用户购物袋更新对象")
public class UserPackageUpDateDTO {

    @ApiModelProperty("购物袋记录id")
    @NotNull(message = "id不能为空")
    private Long id;
    @ApiModelProperty("商品id")
    @NotNull(message = "商品id不能为空")
    private Long itemId;
    @ApiModelProperty("商品数量")
    private Integer itemPiece;
    @ApiModelProperty("所选SKU内容")
    private String skuItemContents;
    @ApiModelProperty("实际单价")
    private String singleActuallyAmount;
    @ApiModelProperty("商品名称")
    private String itemName;
    @ApiModelProperty("商品预览图片地址")
    private String itemImageUrls;

}
