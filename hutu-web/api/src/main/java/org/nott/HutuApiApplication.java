package org.nott;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.nott.feign.BizPayOrderWsClient;
import org.nott.feign.OssClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(clients = { BizPayOrderWsClient.class, OssClient.class })
@MapperScan("org.nott.service.mapper.api")
@SpringBootApplication(scanBasePackages = {"org.nott.common.*",
        "org.nott.external.*",
        "org.nott.web.*",
        "org.nott.service.api.*",
        "org.nott.security.*"})
@Slf4j
public class HutuApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(HutuApiApplication.class,args);
        log.info("hutu-order-api on load");
    }
}