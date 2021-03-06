package io.microservice.doctor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "address")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name = "plot_number")
	@NotEmpty(message = "plotNo is required")
	private String plotNo;
	@Column(name = "town")
	@NotEmpty(message = "town is required")
	private String town;
	@Column(name="city")
	@NotEmpty(message = "city is required")
	private String city;
	@Column(name = "pincode")
	@NotNull(message = "pincode is required")
	private Integer pincode;
}
