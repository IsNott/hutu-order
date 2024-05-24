package org.nott.vo;

import lombok.Data;

/**
 * @author Nott
 * @date 2024-5-24
 */

@Data
public class MenuItemVo {

    private Long itemId;

    private String itemName;

    private String menuCatalogId;

    private String itemDesc;

    private String originAmount;

    private String actuallyAmount;

    private String itemTag;

    private String itemImgeUrls;

}
