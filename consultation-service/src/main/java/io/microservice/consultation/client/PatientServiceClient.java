package io.microservice.consultation.client;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import io.microservice.consultation.model.Patient;
import org.springframework.cache.CacheManager;

@Service
public class PatientServiceClient {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private CacheManager cacheManager;
	
	@HystrixCommand(fallbackMethod = "getFallbackPatient",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")
			})
	@CachePut(value = "patientCache",key = "#phno")
	public Optional<Patient> getPatientDetails(String phno) {
		return Optional.of(restTemplate.getForObject("http://ZUUL-API-GATEWAY-SERVER/api/patient/"+phno, Patient.class));
	}
	
	public Optional<Patient> getFallbackPatient(String phno) {
		Patient data = cacheManager.getCache("patientCache").get(phno,Patient.class);
		if(data!=null)
			return Optional.of(data);
		return Optional.empty();
	}
	
}
