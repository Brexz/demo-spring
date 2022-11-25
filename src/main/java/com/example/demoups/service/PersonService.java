package com.example.demoups.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.example.demoups.exceptions.NotFoundexception;
import com.example.demoups.model.Person;
import com.example.demoups.repository.PersonRepository;

import lombok.Data;

@Data
@Service
public class PersonService {

	//// Prepare the necessary instances
	private final PersonRepository personRepository;
	private final AddressService addressService;

	// Method that return a list of all the persons
	public List<Person> getAllPersons() {
		return StreamSupport.stream(personRepository.findAll().spliterator(), false).collect(Collectors.toList());
	}

	// Method that search and retrieves a Person object if exist, otherwise it will
	// throw and exception
	public Person getPersonById(final int id) {
		final Person person = personRepository.findById(id).orElseThrow(NotFoundexception::new);
		person.setAddresses(Arrays.asList(addressService.getAddress(person.getAddress())));
		return person;
	}

	// Method that delete a person
	public void deletePersonById(final int id) {
		personRepository.deleteById(id);
	}

	// Method that add a new person to the collection
	public Person addNewPerson(final Person person) {
		return personRepository.save(person);
	}

}
