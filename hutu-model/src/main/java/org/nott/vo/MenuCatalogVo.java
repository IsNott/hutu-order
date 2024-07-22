package org.nott.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Nott
 * @date 2024-7-3
 */
@Data
public class MenuCatalogVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long shopId;

    private String catalogName;

    private String catalogDesc;

    private String mark;

    private String imgUrl;

}
