package br.com.thec.cartao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import io.micrometer.core.instrument.MeterRegistry;


@EnableAsync
@SpringBootApplication
@EnableCircuitBreaker
@EnableCaching
public class ApiCartoesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiCartoesApplication.class, args);
	}
	
	@Bean
	MeterRegistryCustomizer<MeterRegistry> configurer(@Value("${spring.application.name}") String applicationName) {
		System.out.println("******************"+applicationName+"******************");
	    return registry -> registry.config().commonTags("application", applicationName);
	}
}