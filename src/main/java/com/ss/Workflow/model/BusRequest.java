package com.ss.Workflow.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BusRequest {
	
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private String busDate;
	
	private String source;
	 
	private String destination;
	 
	private String noOfseats;

}
