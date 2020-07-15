package io.microservice.consultation.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.microservice.consultation.dto.ConsultationDto;
import io.microservice.consultation.exception.ResourceNotFoundException;
import io.microservice.consultation.model.Consultation;
import io.microservice.consultation.service.ConsultationService;

@RestController
@RefreshScope
@RequestMapping("/consultation")
public class ConsultationController {
	
	@Autowired
	private ConsultationService service;
	
	@PostMapping
	public ResponseEntity<Consultation> addPatient(@RequestBody @Valid ConsultationDto consultation){
		return new ResponseEntity<Consultation>(
				service.addConsultation(consultation)
				.orElseThrow(()->new ResourceNotFoundException("data not exists")),
				HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Consultation> getPatient(@PathVariable(name = "id",required = true) int id){
		return new ResponseEntity<Consultation>(service.getConsultation(id).get(), HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Consultation>> getAllPatient(){
		return new ResponseEntity<List<Consultation>>(service.getAllConsultation(), HttpStatus.OK);
	} 
}
