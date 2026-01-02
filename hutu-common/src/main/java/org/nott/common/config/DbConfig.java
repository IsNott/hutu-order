package org.nott.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author tasteTheWorld
 * @date 12月
 * version 1.0.0
 */

@Data
@Component
@ConfigurationProperties(prefix = "spring.datasource")
// TODO 预留MBP代码生成器使用
public class DbConfig {

    private String url;
    private String username;
    private String password;
    private String driverClassName;

}
