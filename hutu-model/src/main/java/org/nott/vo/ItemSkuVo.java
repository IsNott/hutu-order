package org.nott.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.nott.model.BizSkuItem;

import java.util.List;

/**
 * @author Nott
 * @date 2024-6-3
 */
@Data
@ApiModel("商品SKU VO对象")
public class ItemSkuVo {
    @ApiModelProperty("sku分类名称")
    private String skuCatalogName;
    @ApiModelProperty("sku展示顺序")
    private Integer displayOrder;
    @ApiModelProperty("分类id")
    private Long catalogId;
    @ApiModelProperty("sku子项列表")
    private List<BizSkuItem> skuItems;
}
