package io.microservice.doctor.model;

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
@Table(name = "doctor")
public class Doctor implements Serializable{
	private static final long serialVersionUID = 8778880701293254513L;
	@Id
	@Column(name="doctor_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name="doctor_name")
	@NotEmpty(message = "name is required")
	private String name;
	@Column(name = "phone_number",unique = true)
	@NotEmpty(message = "phno is required")
	private String phno;
	@NotEmpty(message = "specalist is required")
	@Column(name = "specalist")
	private String specialist;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn
	@Valid
	private Address address;
}
