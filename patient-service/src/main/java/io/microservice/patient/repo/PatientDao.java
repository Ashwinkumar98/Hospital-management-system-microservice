package io.microservice.patient.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.microservice.patient.model.Patient;

@Repository
public interface PatientDao extends JpaRepository<Patient,Integer>{
	Optional<Patient> findByPhno(String phno);
}
