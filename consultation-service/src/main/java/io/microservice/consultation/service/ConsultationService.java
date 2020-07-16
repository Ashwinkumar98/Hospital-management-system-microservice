package io.microservice.consultation.service;

import java.util.List;
import java.util.Optional;
import io.microservice.consultation.dto.ConsultationDto;
import io.microservice.consultation.model.Consultation;
import io.microservice.consultation.model.Doctor;
import io.microservice.consultation.model.Patient;

public interface ConsultationService {
	Optional<Consultation> addConsultation(ConsultationDto cons);
	Optional<Consultation> getConsultation(int id);
	List<Consultation> getAllConsultation();
	Optional<Consultation> updateConsultation(int id,ConsultationDto cons);
	boolean deleteConsultation(int id);
	List<Patient> getAllPatientsByDoctor(String phno);
	List<Doctor> getAllDoctorsByPatient(String phno);
}
