package org.nott.model;

import lombok.Data;

/**
 * @author Nott
 * @date 2024-6-18
 */

@Data
public class BizSkuCatalogRelation {

    private Long id;

    private Long skuCatalogId;

    private Long skuItemId;

}
