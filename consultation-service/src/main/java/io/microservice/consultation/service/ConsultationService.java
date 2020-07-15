package io.microservice.consultation.service;

import java.util.List;
import java.util.Optional;

import io.microservice.consultation.dto.ConsultationDto;
import io.microservice.consultation.model.Consultation;

public interface ConsultationService {
	Optional<Consultation> addConsultation(ConsultationDto cons);
	Optional<Consultation> getConsultation(int id);
	List<Consultation> getAllConsultation();
}