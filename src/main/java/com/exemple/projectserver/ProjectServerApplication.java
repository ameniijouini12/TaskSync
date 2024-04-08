package com.exemple.projectserver;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class ProjectServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectServerApplication.class, args);
	}

}
