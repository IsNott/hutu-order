package org.nott.common.config;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

/**
 * @author Nott
 * @date 2024-11-18
 */
@Data
@ConditionalOnProperty(prefix = "business")
public class BusinessConfig {

    private String prefixEatIn;

    private String prefixTakeOut;

    private Long orderExpire;

    private Integer orderLength;
}
