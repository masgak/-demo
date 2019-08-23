package com.bosssoft.hr.train.vue_category_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RestController;


@EnableEurekaClient
@RestController

@SpringBootApplication
public class VueCategoryServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(VueCategoryServerApplication.class, args);
    }

}
