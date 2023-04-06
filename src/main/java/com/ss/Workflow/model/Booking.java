package com.ss.Workflow.model;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {
	 
	private Long bookingNo;
	
	private String busNo;
	 
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private String busDate;
	
	private String source;
	
	private String destination;
	 
	private String noOfseats;
	
	private String status;

    private List<Passenger> passengerlist;

}
