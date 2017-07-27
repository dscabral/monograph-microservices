package com.monografia.movimentarestoque;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MovimentarEstoqueApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovimentarEstoqueApplication.class, args);
	}
}
