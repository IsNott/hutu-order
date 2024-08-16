package org.nott.model;

import lombok.Data;

/**
 * sku-子项关系表
 * @author Nott
 * @date 2024-6-18
 */

@Data
public class BizSkuCatalogRelation {

    private Long id;

    /**
     * sku分类id
     */
    private Long skuCatalogId;

    /**
     * sku子项id
     */
    private Long skuItemId;

}
