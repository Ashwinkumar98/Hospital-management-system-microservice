package io.microservice.consultation.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.microservice.consultation.model.Consultation;

@Repository
public interface ConsultationDao extends JpaRepository<Consultation,Integer>{

}
