package io.microservice.doctor.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.microservice.doctor.model.Doctor;
import io.microservice.doctor.repo.DoctorRepo;

@Service
public class DoctorServiceImp implements DoctorService{
	
	@Autowired
	private DoctorRepo dao;

	@Override
	public Optional<Doctor> addDoctor(Doctor doctor) {
		if(dao.findByPhno(doctor.getPhno()).isPresent())
			return Optional.empty();
		return Optional.of(dao.save(doctor));
	}

	@Override
	public Optional<Doctor> getDoctor(String phno) {
		return dao.findByPhno(phno);
	}

	@Override
	public List<Doctor> getAllDoctors() {
		return dao.findAll();
	}

	@Override
	public Optional<Doctor> updateDoctor(int id, Doctor doctor) {
		if(dao.existsById(id)) {
			doctor.setId(id);
			return Optional.of(dao.save(doctor));
		}
		return Optional.empty();
	}

}
