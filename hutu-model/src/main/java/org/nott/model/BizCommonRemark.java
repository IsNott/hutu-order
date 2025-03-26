package org.nott.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 常用备注
 * @author Nott
 * @date 2025-3-24
 */

@Data
public class BizCommonRemark implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 内容
     */
    private String context;

    /**
     * 删除标识
     */
    private Integer delFlag;
}
