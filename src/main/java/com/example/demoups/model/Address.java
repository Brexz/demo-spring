package com.example.demoups.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {

	//This is a class that contains all the fields needed to manipulate the address object 
	private int id;
	private String street;
	private int number;
	private String city;
	private int zipcode;

}
