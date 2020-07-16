package io.microservice.doctor.service;

import java.util.List;
import java.util.Optional;

import io.microservice.doctor.model.Doctor;

public interface DoctorService {
	
	Optional<Doctor> addDoctor(Doctor doctor);
	Optional<Doctor> getDoctor(String phno);
	List<Doctor> getAllDoctors();
	Optional<Doctor> updateDoctor(int id,Doctor doctor);
}
