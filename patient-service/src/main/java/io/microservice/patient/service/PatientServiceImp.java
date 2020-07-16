package io.microservice.patient.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.microservice.patient.model.Patient;
import io.microservice.patient.repo.PatientDao;

@Service
public class PatientServiceImp implements PatientService{
	
	@Autowired
	private PatientDao dao;

	@Override
	public Optional<Patient> addPatient(Patient patient) {
		if(dao.findByPhno(patient.getPhno()).isPresent())
			return Optional.empty();
		return Optional.of(dao.save(patient));
	}

	@Override
	public Optional<Patient> getPatient(String phno) {
		return dao.findByPhno(phno);
	}

	@Override
	public List<Patient> getAllPatient() {
		return dao.findAll();
	}

	@Override
	public Optional<Patient> updatePatient(int id,Patient patient) {
		if(dao.existsById(id)) {
			patient.setId(id);
			return Optional.of(dao.save(patient));
		}
		return Optional.empty();
	}

}
