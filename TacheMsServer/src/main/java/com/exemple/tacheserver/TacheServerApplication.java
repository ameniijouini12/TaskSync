package com.exemple.tacheserver;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class TacheServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TacheServerApplication.class, args);
	}

}
