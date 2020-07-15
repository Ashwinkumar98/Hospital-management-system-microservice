package io.microservice.diagnosis.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="diagnosis")
public class Diagnosis implements Serializable{
	private static final long serialVersionUID = 8557703228225354477L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="diagnosis_id")
	private int id;
	@Column(name="name")
	@NotEmpty(message = "name is required")
	private String name;
	@Column(name = "description")
	@NotEmpty(message = "description is required")
	private String description;
}
