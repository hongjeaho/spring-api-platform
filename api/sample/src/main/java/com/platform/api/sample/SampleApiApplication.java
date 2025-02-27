package com.platform.api.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.platform.common.base",
        "com.platform.datasource.base",
        "com.platform.common.web",
        "com.platform.api.sample",
})
public class SampleApiApplication {
    public static void main(final String[] args) {
        SpringApplication.run(SampleApiApplication.class, args);
    }
}