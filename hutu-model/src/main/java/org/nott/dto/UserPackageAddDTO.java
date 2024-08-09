package org.nott.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Nott
 * @date 2024-6-3
 */

@Data
@ApiModel("用户购物袋DTO")
public class UserPackageAddDTO {

//    @NotNull(message = "用户id不能为空")
//    private Long userId;

    @ApiModelProperty(value = "商品id",required = true)
    @NotNull(message = "产品id不能为空")
    private Long itemId;

    @ApiModelProperty(value = "数量",required = true)
    @NotNull(message = "产品数量不能为空")
    private Integer itemPiece;

    @ApiModelProperty(value = "SKU正文，逗号分割")
    private String skuItemContents;
}
