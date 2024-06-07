package org.nott;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("org.nott.admin.mapper")
@SpringBootApplication(scanBasePackages = {"org.nott.admin","org.nott.security","org.nott.common"})
public class HutuAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(HutuAdminApplication.class,args);
    }
}