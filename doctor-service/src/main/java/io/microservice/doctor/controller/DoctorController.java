package io.microservice.doctor.controller;

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

import io.microservice.doctor.exception.ResourceAlreadyFoundException;
import io.microservice.doctor.exception.ResourceNotFoundException;
import io.microservice.doctor.model.Doctor;
import io.microservice.doctor.service.DoctorService;

@RestController
@RefreshScope
public class DoctorController {
	
	@Autowired
	private DoctorService service;
	
	@PostMapping
	public ResponseEntity<Doctor> addPatient(@RequestBody @Valid Doctor doctor){
		return new ResponseEntity<Doctor>(
				service.addDoctor(doctor)
				.orElseThrow(()->new ResourceAlreadyFoundException("doctor already exists")),
				HttpStatus.OK);
	}
	
	@GetMapping("/{phno}")
	public ResponseEntity<Doctor> getPatient(@PathVariable(name = "phno",required = true) String phno){
		return new ResponseEntity<Doctor>(
				service.getDoctor(phno)
				.orElseThrow(()->new ResourceNotFoundException("doctor not exists")),
				HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Doctor>> getAllPatient(){
		return new ResponseEntity<List<Doctor>>(service.getAllDoctors(), HttpStatus.OK);
	} 
	
	@PutMapping("/{id}")
	public ResponseEntity<Doctor> updateDoctor(@PathVariable(name = "id",required = true) int id,@RequestBody @Valid Doctor doctor){
		return new ResponseEntity<Doctor>(
				service.updateDoctor(id, doctor)
				.orElseThrow(()->new ResourceNotFoundException("doctor not exists")),
				HttpStatus.OK);
	}
}
