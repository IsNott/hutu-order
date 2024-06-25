package org.nott.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Nott
 * @date 2024-6-3
 */

@Data
public class UserPackageAddDTO {

//    @NotNull(message = "用户id不能为空")
//    private Long userId;

    @NotNull(message = "产品id不能为空")
    private Long itemId;

    @NotNull(message = "产品数量不能为空")
    private Integer itemPiece;

    private String skuItemContents;
}
