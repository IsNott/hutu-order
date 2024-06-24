package org.nott.vo;

import lombok.Data;
import org.nott.model.BizSkuItem;

import java.util.List;

/**
 * @author Nott
 * @date 2024-6-18
 */

@Data
public class SkuCatalogItemVo {

    private Long skuCatalogId;

    private String skuCatalogName;

    private Integer disPlayOrder;

    private Long itemId;

    private String itemName;

    private List<BizSkuItem> skuItems;
}
