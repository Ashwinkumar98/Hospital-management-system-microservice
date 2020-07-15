package io.microservice.patient.service;

import java.util.List;
import java.util.Optional;

import io.microservice.patient.model.Patient;

public interface PatientService {
	Optional<Patient> addPatient(Patient patient);
	Optional<Patient> getPatient(String phno);
	List<Patient> getAllPatient();
}
