package org.nott.vo;

import lombok.Data;

/**
 * @author Nott
 * @date 2024-6-26
 */

@Data
public class PayWayVo {

    private Long id;

    private String paymentName;

    private Integer order;

    private String icon;

    private Integer packUp;

}
