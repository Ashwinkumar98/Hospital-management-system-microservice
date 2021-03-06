package io.microservice.patient.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="patient")
public class Patient  implements Serializable{
	private static final long serialVersionUID = -804163055574488821L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "patient_id")
	private int id;
	@NotEmpty(message = "name is required")
	@Column(name = "patient_name")
	private String name;
	@Column(name = "phone_number",unique = true)
	@NotEmpty(message = "phno is required")
	private String phno;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	@Valid
	private Address address;
}
