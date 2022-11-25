package com.example.demoups.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="PERSON")
public class Person {
	
	//This is a class that contains all the needed fields to store the persons data
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Pattern(regexp = "^[A-Za-z]+$", message = "Invalid name")
	@Column(name="NAME")
	private String name;
	
	@Column(name="AGE")
	private int age;
	
	@Column(name="DATE_JOINED")
	private String dateJoined;
	
	@Column(name="GENDER")
	private String gender;
	
	@Column(name="TELEPHONE")
	private long telephone;
	
	@JsonIgnore
	@Column(name="ADDRESS")
	private String address;
	
	@Transient
	private List<Address> addresses;
	
}
