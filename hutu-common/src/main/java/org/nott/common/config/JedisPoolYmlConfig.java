package org.nott.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Nott
 * @date 2024-6-12
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.redis.jedis.pool")
public class JedisPoolYmlConfig {

    private Integer maxIdle;

    private Integer minIdle;

    private Integer maxActive;

    private Long TimeOut;

}
