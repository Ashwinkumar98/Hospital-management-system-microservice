package io.microservice.consultation.client;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import io.microservice.consultation.model.Diagnosis;

@Service
public class DiagnosisServiceClient {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private CacheManager cacheManager;

	@HystrixCommand(fallbackMethod = "getFallbackDiagnosis",commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")
	})
	@CachePut(value = "diagnosisCache",key = "#id")
	public Optional<Diagnosis> getDiagnosisDetails(int id) {
		return Optional.of(restTemplate.getForObject("http://ZUUL-API-GATEWAY-SERVER/api/diagnosis/"+id,Diagnosis.class));
	}
	

	public Optional<Diagnosis> getFallbackDiagnosis(int id) {
		Diagnosis data = cacheManager.getCache("diagnosisCache").get(id, Diagnosis.class);
		if(data!=null)
			return Optional.of(data);
		return Optional.empty();
	}
}
