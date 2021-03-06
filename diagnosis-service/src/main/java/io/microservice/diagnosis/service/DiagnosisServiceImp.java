package io.microservice.diagnosis.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.microservice.diagnosis.model.Diagnosis;
import io.microservice.diagnosis.repo.DiagnosisDao;

@Service
public class DiagnosisServiceImp implements DiagnosisService{
	
	@Autowired
	private DiagnosisDao dao;

	@Override
	public Optional<Diagnosis> addDiagnosis(Diagnosis diagnosis) {
		if(dao.findByName(diagnosis.getName()).isPresent())
			return Optional.empty();
		return Optional.of(dao.save(diagnosis));
	}

	@Override
	public Optional<Diagnosis> getDiagnosis(int id) {
		return dao.findById(id);
	}

	@Override
	public List<Diagnosis> getAllDiagnosis() {
		return dao.findAll();
	}

	@Override
	public Optional<Diagnosis> updateDiagnosis(int id, Diagnosis diagnosis) {
		if(dao.existsById(id)) {
			diagnosis.setId(id);
			return Optional.of(dao.save(diagnosis));
		}
		return Optional.empty();
	}
}
