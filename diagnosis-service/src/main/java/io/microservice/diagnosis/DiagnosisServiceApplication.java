package io.microservice.diagnosis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DiagnosisServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiagnosisServiceApplication.class, args);
	}

}
