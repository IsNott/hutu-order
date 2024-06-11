package org.nott.vo;

import lombok.Data;

/**
 * @author Nott
 * @date 2024-6-6
 */

@Data
public class AdminTokenVo {

    private String tokenValue;

    private String tokenName;

    private Object loginId;
}
