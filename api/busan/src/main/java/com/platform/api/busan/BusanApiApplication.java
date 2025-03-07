package com.platform.api.busan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.platform.common.base",
        "com.platform.datasource.base",
        "com.platform.common.web",
        "com.platform.api.busan",
})
public class BusanApiApplication {
    public static void main(final String[] args) {
        SpringApplication.run(BusanApiApplication.class, args);
    }
}