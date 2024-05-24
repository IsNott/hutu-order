package org.nott;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"org.nott.common.*","org.nott.web.*"})
@Slf4j
public class HutuApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(HutuApiApplication.class,args);
        log.info("hutu-order on load");
    }
}