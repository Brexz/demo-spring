package com.example.demoups.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoups.model.Person;
import com.example.demoups.service.PersonService;

import lombok.Data;

@Data
@Validated
@RestController
@RequestMapping("/api")
public class PersonController {

	//This injects the service dependency
	private final PersonService personService;

	//Function that return a list of all the persons 
	@GetMapping("/getAllPersons")
	public List<Person> getAllPersons() {
		return personService.getAllPersons();
	}

	//Function that given an id, return the person if is available
	@GetMapping("/getPersonById/{id}")
	public Person getPersonById(@PathVariable final int id) {
		return personService.getPersonById(id);
	}
	
	//Function that add a new person to the DB
	@PostMapping("/addNewPerson")
	public Person addNewPerson(@Valid @RequestBody final Person person) {
		return personService.addNewPerson(person);
	}

	//Function that modify a person
	@PutMapping("/modifyPerson")
	public Person modifyPerson(@Valid @RequestBody final Person person) {
		return personService.addNewPerson(person);
	}

	//Function that delete a person given the id
	@DeleteMapping("/deletePersonById/{id}")
	public void deletePersonById(@PathVariable final int id) {
		personService.deletePersonById(id);
	}

}
