package com.student.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ExceptionDTO {
	
	private LocalDateTime dateTime;
	
	private String message;
	
	private String description;

}
