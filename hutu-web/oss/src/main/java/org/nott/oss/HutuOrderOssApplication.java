package org.nott.oss;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.nott.feign.OssClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Nott
 * @date 2025-3-18
 */

@Slf4j
@EnableFeignClients(clients = OssClient.class)
@MapperScan("org.nott.service.mapper.oss")
@SpringBootApplication(scanBasePackages = {
        "org.nott.oss",
        "org.nott.service.oss",
        "org.nott.common.config",
        "org.nott.common.handler"})
public class HutuOrderOssApplication {

    public static void main(String[] args) {
        SpringApplication.run(HutuOrderOssApplication.class, args);
    }
}
