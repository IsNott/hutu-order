package org.nott.vo;

import lombok.Data;

/**
 * @author Nott
 * @date 2024-7-3
 */
@Data
public class MenuCatalogVo {

    private Long id;

    private Long shopId;

    private String catalogName;

    private String catalogDesc;

    private String mark;

    private String imgUrl;

}
