package org.nott.vo;

import lombok.Data;
import org.nott.model.BizSkuItem;

import java.util.List;

/**
 * @author Nott
 * @date 2024-6-3
 */
@Data
public class ItemSkuVo {

    private String skuCatalogName;

    private Integer displayOrder;

    private Long catalogId;

    private List<BizSkuItem> skuItems;
}
