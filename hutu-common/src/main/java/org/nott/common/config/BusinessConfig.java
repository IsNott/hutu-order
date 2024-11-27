package org.nott.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Nott
 * @date 2024-11-18
 */
@Data
@ConfigurationProperties(prefix = "business")
@Component
public class BusinessConfig {

    private String prefixEatIn;

    private String prefixTakeOut;

    private Long orderExpire;

    private Integer orderLength;

    private String checkType;
}
