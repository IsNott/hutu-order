package org.nott.external.wechat.config;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Nott
 * @date 2024-6-14
 */

@Data
@Component
@ConfigurationProperties(prefix = "wechat.app")
public class WechatAppConfig {

    private String appId;

    private String appSecret;
}
