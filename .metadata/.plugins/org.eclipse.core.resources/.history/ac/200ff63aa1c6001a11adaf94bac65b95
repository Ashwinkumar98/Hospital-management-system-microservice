package io.microservice.consultation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="consultation")
public class Consultation {
	
	@Id
	@Column(name = "constultation_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int consultationId;
	@OneToOne
	@JoinColumn(name="doctor_id")
	private Doctor doctor;
	@OneToOne
	@JoinColumn(name="patient_id")
	private Patient patient;
	@OneToOne
	@JoinColumn(name = "diagnosis_id")
	private Diagnosis diagnosis;
}
