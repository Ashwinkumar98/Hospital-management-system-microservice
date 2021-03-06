package io.microservice.consultation.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "doctor")
public class Doctor {
	
	@Id
	@Column(name="doctor_id")
	private int id;
	@Column(name="doctor_name")
	private String name;
	@Column(name = "phone_number")
	private String phno;
	@Column(name = "specalist")
	private String specialist;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn
	private Address address;
}
