package org.nott;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.nott")
public class HutuApplication {

    public static void main(String[] args) {
        SpringApplication.run(HutuApplication.class,args);
    }
}