package org.nott.vo;

import lombok.Data;

/**
 * @author Nott
 * @date 2024-6-3
 */
@Data
public class UserPackageVo {

    private Long id;

    private Long userId;

    private Long itemId;

    private Integer itemPiece;

    private String skuItemContents;

    private String singleActuallyAmount;

    private String itemName;
}
