package io.microservice.doctor.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ResponseTypeDTO implements Serializable {
	
	private static final long serialVersionUID = -2307425779840174124L;
	
    @JsonProperty("errorMessage")
    private String errMsg;
    
    @JsonProperty("errorDetails")
    private String errDetail;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonProperty("timestamp")
    private LocalDateTime timestamp;
   
    @JsonProperty("status")
    private Integer status;

}
