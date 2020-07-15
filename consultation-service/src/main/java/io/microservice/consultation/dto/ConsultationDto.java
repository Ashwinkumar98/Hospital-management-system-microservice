package io.microservice.consultation.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ConsultationDto implements Serializable {
	private static final long serialVersionUID = 6784467401439494420L;
	@NotEmpty(message = "patientPhno is required")
	private String patientPhno;
	@NotEmpty(message = "doctorPhno is required")
	private String doctorPhno;
	@NotNull(message = "diagnosisId is required")
	private Integer diagnosisId;
}
