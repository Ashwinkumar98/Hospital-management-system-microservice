package io.microservice.consultation.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.microservice.consultation.client.DiagnosisServiceClient;
import io.microservice.consultation.client.DoctorServiceClient;
import io.microservice.consultation.client.PatientServiceClient;
import io.microservice.consultation.dto.ConsultationDto;
import io.microservice.consultation.model.Consultation;
import io.microservice.consultation.model.Diagnosis;
import io.microservice.consultation.model.Doctor;
import io.microservice.consultation.model.Patient;
import io.microservice.consultation.repo.ConsultationDao;

@Service
public class ConsultationServiceImp implements ConsultationService{
	
	@Autowired
	private ConsultationDao dao;
	
	@Autowired
	private PatientServiceClient patientService;
	
	@Autowired
	private DoctorServiceClient doctorService;
	
	@Autowired
	private DiagnosisServiceClient diagnosisService;

	
	@Override
	public Optional<Consultation> addConsultation(ConsultationDto cons) {
		Consultation consultation = new Consultation();
		Optional<Patient> patient= patientService.getPatientDetails(cons.getPatientPhno());
		Optional<Doctor> doctor = doctorService.getDoctorDetalis(cons.getDoctorPhno());
		Optional<Diagnosis> diagnosis = diagnosisService.getDiagnosisDetails(cons.getDiagnosisId());
		if(patient.isPresent() && doctor.isPresent() && diagnosis.isPresent()) {
			consultation.setPatient(patient.get());
			consultation.setDoctor(doctor.get());
			consultation.setDiagnosis(diagnosis.get());
			return Optional.of(dao.save(consultation));
		}
		return Optional.empty();
	}
	
	
	@Override
	public Optional<Consultation> getConsultation(int id) {
		return dao.findById(id);
	}

	@Override
	public List<Consultation> getAllConsultation() {
		return dao.findAll();
	}


	@Override
	public Optional<Consultation> updateConsultation(int id, ConsultationDto cons) {
		Optional<Consultation> data = getConsultation(id);
		if(data.isPresent()) {
			data.get().setConsultationId(id);
			Optional<Patient> patient= patientService.getPatientDetails(cons.getPatientPhno());
			Optional<Doctor> doctor = doctorService.getDoctorDetalis(cons.getDoctorPhno());
			Optional<Diagnosis> diagnosis = diagnosisService.getDiagnosisDetails(cons.getDiagnosisId());
			if(patient.isPresent() && doctor.isPresent() && diagnosis.isPresent()) {
				data.get().setPatient(patient.get());
				data.get().setDoctor(doctor.get());
				data.get().setDiagnosis(diagnosis.get());
				return Optional.of(dao.save(data.get()));
			}
			return Optional.empty();
		}
		return Optional.empty();
	}


	@Override
	public boolean deleteConsultation(int id) {
		if(getConsultation(id).isPresent()) {
			dao.deleteById(id);
			return true;
		}
		return false;
	}


	@Override
	public List<Patient> getAllPatientsByDoctor(String phno) {
		return dao.findByDoctor_Phno(phno).stream().map(c->c.getPatient()).collect(Collectors.toList());
	}


	@Override
	public List<Doctor> getAllDoctorsByPatient(String phno) {
		return dao.findByPatient_Phno(phno).stream().map(c->c.getDoctor()).collect(Collectors.toList());
	}
}
