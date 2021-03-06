package io.microservice.diagnosis.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.microservice.diagnosis.exception.ResourceAlreadyFoundException;
import io.microservice.diagnosis.exception.ResourceNotFoundException;
import io.microservice.diagnosis.model.Diagnosis;
import io.microservice.diagnosis.service.DiagnosisService;


@RestController
@RefreshScope
public class DiagnosisController {
	
	@Autowired
	private DiagnosisService service;
	
	@PostMapping
	public ResponseEntity<Diagnosis> addPatient(@RequestBody @Valid Diagnosis diagnosis){
		return new ResponseEntity<Diagnosis>(
				service.addDiagnosis(diagnosis)
				.orElseThrow(()->new ResourceAlreadyFoundException("diagnosis already exists")),
				HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Diagnosis> getPatient(@PathVariable(name = "id",required = true) int id){
		return new ResponseEntity<Diagnosis>(
				service.getDiagnosis(id)
				.orElseThrow(()->new ResourceNotFoundException("diagnosis not exists")),
				HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Diagnosis>> getAllPatient(){
		return new ResponseEntity<List<Diagnosis>>(service.getAllDiagnosis(), HttpStatus.OK);
	} 
	
	@PutMapping("/{id}")
	public ResponseEntity<Diagnosis> updateDiagnosis(@PathVariable(name = "id",required = true) int id,@RequestBody @Valid Diagnosis diagnosis){
		return new ResponseEntity<Diagnosis>(
				service.updateDiagnosis(id, diagnosis)
				.orElseThrow(()->new ResourceNotFoundException("doctor not exists")),
				HttpStatus.OK);
	}

}
