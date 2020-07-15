package io.microservice.diagnosis.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.microservice.diagnosis.model.Diagnosis;

@Repository
public interface DiagnosisDao extends JpaRepository<Diagnosis,Integer> {
	Optional<Diagnosis> findByName(String name);
}
