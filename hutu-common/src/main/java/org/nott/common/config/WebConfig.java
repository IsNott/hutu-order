package org.nott.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Nott
 * @date 2025-3-18
 */
@Data
@Component
@ConfigurationProperties(prefix = "web")
public class WebConfig {

    private String basePath;

    private String uploadPath;

    private String proxySuffix;
}
