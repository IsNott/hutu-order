package org.nott.external.alipay.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Nott
 * @date 2024-6-14
 */

@Data
@Component
@ConfigurationProperties(prefix = "alipay")
public class AlipayConfig {

    private String appId;

    private String privateKey;

    private String publicKey;

    private String signType;

    private String notifyUrl;
}
