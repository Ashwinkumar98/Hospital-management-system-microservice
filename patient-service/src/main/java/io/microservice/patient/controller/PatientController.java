package io.microservice.patient.controller;

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

import io.microservice.patient.exception.ResourceAlreadyFoundException;
import io.microservice.patient.exception.ResourceNotFoundException;
import io.microservice.patient.model.Patient;
import io.microservice.patient.service.PatientService;

@RestController
@RefreshScope
public class PatientController {
	
	@Autowired
	private PatientService service;
	
	
	@PostMapping
	public ResponseEntity<Patient> addPatient(@RequestBody @Valid Patient patient){
		return new ResponseEntity<Patient>(
				service.addPatient(patient)
				.orElseThrow(()->new ResourceAlreadyFoundException("patient already exists")),
				HttpStatus.OK);
	}
	
	@GetMapping("/{phno}")
	public ResponseEntity<Patient> getPatient(@PathVariable(name = "phno",required = true) String phno){
		return new ResponseEntity<Patient>(
				service.getPatient(phno)
				.orElseThrow(()->new ResourceNotFoundException("patient not found")), 
				HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Patient>> getAllPatient(){
		return new ResponseEntity<List<Patient>>(service.getAllPatient(), HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Patient> updatePatient(@PathVariable(name="id",required = true) int id,@RequestBody @Valid Patient patient){
		return new ResponseEntity<Patient>(
				service.updatePatient(id, patient)
				.orElseThrow(()->new ResourceNotFoundException("patient not found")),
				HttpStatus.OK);
	}
}
