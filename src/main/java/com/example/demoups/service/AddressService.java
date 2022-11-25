package com.example.demoups.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demoups.model.Address;

import lombok.Data;

@Data
@Service
public class AddressService {
	
	//This contains an string that have the url endpoint to consume for the address
	private final String url = "http://localhost:8010/api";
	
	//Create a variable to create a RestTemplate to consume the endpoint
	private final RestTemplate template;
	
	//Function that return an address object  
	public Address getAddress (String id) {
		return template.getForObject(url.concat("/getAddress/" + id), Address.class);
	}
	
	//Function that return a list of all address 
	public List<Address> getAllAddresses () {
		return Arrays.asList(template.getForObject(url.concat("/getAllAddresses"), Address[].class));
	}
}
