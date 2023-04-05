package com.ss.Workflow.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ss.Workflow.model.BusRequest;

@RestController
public class BookingWorkflowController {
	
	@Autowired
	   RestTemplate restTemplate;

	   @RequestMapping(value = "/template/products", method = RequestMethod.POST)
	   public String bookTicket(@RequestBody BusRequest busRequest) {
	      HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<BusRequest> entity = new HttpEntity<BusRequest>(busRequest,headers);
	      
	      return restTemplate.exchange(
	         "http://localhost:8080/products", HttpMethod.POST, entity, String.class).getBody();
	      
	      
	   }

}
