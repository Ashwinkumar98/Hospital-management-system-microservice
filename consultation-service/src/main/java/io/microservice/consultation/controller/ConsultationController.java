package io.microservice.consultation.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.microservice.consultation.dto.ConsultationDto;
import io.microservice.consultation.exception.ResourceNotFoundException;
import io.microservice.consultation.model.Consultation;
import io.microservice.consultation.model.Doctor;
import io.microservice.consultation.model.Patient;
import io.microservice.consultation.service.ConsultationService;

@RestController
@RefreshScope
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
		return new ResponseEntity<Consultation>(
				service.getConsultation(id)
				.orElseThrow(()->new ResourceNotFoundException("data not found")),
				HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Consultation>> getAllPatient(){
		return new ResponseEntity<List<Consultation>>(service.getAllConsultation(), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteConsultation(@PathVariable(name = "id",required = true) int id){
		if(service.deleteConsultation(id)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		throw new ResourceNotFoundException("data not found");
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Consultation> updateConsultation(@PathVariable(name="id",required = true) int id,@RequestBody @Valid ConsultationDto cons){
		return new ResponseEntity<Consultation>(
				service.updateConsultation(id, cons)
				.orElseThrow(()->new ResourceNotFoundException("data not exists")),
				HttpStatus.OK);
	}
	
	@GetMapping("/doctor/{phno}")
	public ResponseEntity<List<Patient>> getAllPatientByDoctor(@PathVariable(name="phno",required = true) String phno){
		return new ResponseEntity<List<Patient>>(service.getAllPatientsByDoctor(phno), HttpStatus.OK);
	}
	
	@GetMapping("/patient/{phno}")
	public ResponseEntity<List<Doctor>> getAllDoctorsByPatientr(@PathVariable(name="phno",required = true) String phno){
		return new ResponseEntity<List<Doctor>>(service.getAllDoctorsByPatient(phno), HttpStatus.OK);
	}
}
