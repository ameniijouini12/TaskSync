package com.example.msproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@SpringBootApplication
@EnableEurekaServer
public class MsProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsProjectApplication.class, args);
    }

}
