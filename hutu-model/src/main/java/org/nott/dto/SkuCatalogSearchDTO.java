package org.nott.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

/**
 * @author Nott
 * @date 2024-6-18
 */
@ApiModel("sku分类查询DTO")
@Data
public class SkuCatalogSearchDTO {

    @ApiModelProperty("商品名称")
    private String itemName;

    @ApiModelProperty("sku分类名称")
    private String catalogName;

}
