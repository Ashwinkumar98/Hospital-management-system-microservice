package io.microservice.consultation.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import io.microservice.consultation.model.Consultation;

@Repository
public interface ConsultationDao extends JpaRepository<Consultation,Integer>{
	List<Consultation> findByPatient_Phno(String phno);
	List<Consultation> findByDoctor_Phno(String phno);
}
