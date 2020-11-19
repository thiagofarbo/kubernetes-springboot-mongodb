package br.com.thec.cartao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.scheduling.annotation.EnableAsync;


@EnableAsync
@SpringBootApplication
//@EnableHystrixDashboard
@EnableCircuitBreaker
@EnableCaching
public class ApiCartoesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiCartoesApplication.class, args);
	}
}