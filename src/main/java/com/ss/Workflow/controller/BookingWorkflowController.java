package com.ss.Workflow.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ss.Workflow.model.Booking;
import com.ss.Workflow.model.BusRequest;

@RestController
public class BookingWorkflowController {
	
	@Autowired
	   RestTemplate restTemplate;
	
	@Value("${spring.service.registry}")
	String issueUri;

	   @RequestMapping(value = "/template/booking", method = RequestMethod.POST)
	   public String bookTicket(@RequestBody BusRequest busRequest) {
	      
		   
		  String inventoryseats = extracted(busRequest);
		  
		  HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<BusRequest> entity = new HttpEntity<BusRequest>(busRequest,headers);
		  
		return inventoryseats;
	 	      
	 	      
	      
	      
	   }

	private String extracted(BusRequest busRequest) {
		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<BusRequest> entity = new HttpEntity<BusRequest>(busRequest,headers);
	      
	      String bookingBusID = restTemplate.exchange(
	    		  issueUri +"/busroutes/"+busRequest.getSource()+"/"+busRequest.getDestination(), HttpMethod.GET, entity, String.class).getBody();
	      
	      String inventoryseats = restTemplate.exchange(
	    		  issueUri +"/jms/inventory"+bookingBusID, HttpMethod.GET, entity, String.class).getBody();
	      
	      Booking book = new Booking();
	      book.setBusNo(bookingBusID);
	      book.setSource(busRequest.getSource());
	      book.setDestination(busRequest.getDestination());
	      book.setPassengerlist(busRequest.getPassengerlist());
	      book.setStatus("PENDING");
	      book.setNoOfseats(busRequest.getNoOfseats());
	      
	      HttpHeaders postheaders = new HttpHeaders();
	      postheaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<Booking> postentity = new HttpEntity<Booking>(book,postheaders);
	      
	      ResponseEntity<String> booking = restTemplate.exchange(
	    		  issueUri +"/booking", HttpMethod.POST, postentity, String.class);
	      
		return inventoryseats;
	}

}
