package org.nott.oss;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.nott.feign.OssClient;
import org.nott.service.admin.SysUserService;
import org.nott.service.mapper.admin.SysUserMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Nott
 * @date 2025-3-18
 */

@Slf4j
@EnableFeignClients(clients = OssClient.class)
@MapperScans(value = {
        @MapperScan("org.nott.service.mapper.oss"),
        @MapperScan("org.nott.service.mapper.admin")
})
@SpringBootApplication(scanBasePackages = {
        "org.nott.common.*",
        "org.nott.oss",
        "org.nott.service.oss",
        "org.nott.security.*",
        "org.nott.common.handler"},
        // 使用后台的auth鉴权
        scanBasePackageClasses = { SysUserService.class, SysUserMapper.class })
public class HutuOssApplication {

    public static void main(String[] args) {

        SpringApplication.run(HutuOssApplication.class, args);

        log.info("Hutu-Order-Oss started successfully");
    }
}
