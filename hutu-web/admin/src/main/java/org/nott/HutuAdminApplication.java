package org.nott;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.nott.feign.BizPayOrderWsClient;
import org.nott.feign.OssClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@EnableFeignClients(clients = {BizPayOrderWsClient.class, OssClient.class})
@MapperScan("org.nott.service.mapper.**")
@SpringBootApplication(scanBasePackages = {
        "org.nott.admin.*",
        "org.nott.security.*",
        "org.nott.common.*",
        "org.nott.service.**.*"})
@Slf4j
public class HutuAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(HutuAdminApplication.class, args);
        log.info("hutu-admin-api on load");
    }
}