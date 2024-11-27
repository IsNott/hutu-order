package org.nott.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Nott
 * @date 2024-11-26
 */
@Data
@Component
@ConfigurationProperties("ws")
public class WebSocketConfig {

    private Integer port;

    private String path;

    private Integer workerGroup;

    private Integer bossGroup;

    private String heartBeatMsg;

    private String heartBeatResp;

    private boolean showIdleMsg;
}
