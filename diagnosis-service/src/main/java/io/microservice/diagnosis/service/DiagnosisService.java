package io.microservice.diagnosis.service;

import java.util.List;
import java.util.Optional;

import io.microservice.diagnosis.model.Diagnosis;

public interface DiagnosisService {
	
	Optional<Diagnosis> addDiagnosis(Diagnosis diagnosis);
	Optional<Diagnosis> getDiagnosis(int id);
	List<Diagnosis> getAllDiagnosis();
	Optional<Diagnosis> updateDiagnosis(int id,Diagnosis diagnosis);
}
