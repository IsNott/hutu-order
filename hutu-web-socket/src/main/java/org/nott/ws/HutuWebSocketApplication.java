package org.nott.ws;

import lombok.extern.slf4j.Slf4j;
import org.nott.feign.BizPayOrderWsClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@Slf4j
@SpringBootApplication(scanBasePackages = {"org.nott.ws", "org.nott.common.config", "org.nott.common.utils"})
@EnableFeignClients(clients = BizPayOrderWsClient.class)
@EnableAsync
public class HutuWebSocketApplication {

    public static void main(String[] args) {
        SpringApplication.run(HutuWebSocketApplication.class, args);
        log.info("Hutu-WebSocket 服务启动");
    }
}