package org.nott.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author Nott
 * @date 2024-6-3
 */
@Data
@ApiModel("用户购物袋返回VO")
public class UserPackageVo {

    @ApiModelProperty("购物袋记录id")
    private Long id;
    @ApiModelProperty("用户id")
    private Long userId;
    @ApiModelProperty("商品id")
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
    private List<String> itemImageUrls;
    @ApiModelProperty("预计制作时间")
    private Integer expectMakeTime;
}
