package io.microservice.doctor.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.microservice.doctor.model.Doctor;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Integer>{
	Optional<Doctor> findByPhno(String phno);
}
