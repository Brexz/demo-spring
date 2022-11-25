package com.example.demoups;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import java.util.List;

import com.example.demoups.exceptions.NotFoundexception;
import com.example.demoups.model.Address;
import com.example.demoups.model.Person;
import com.example.demoups.rest.PersonController;
import com.example.demoups.service.PersonService;

@ExtendWith(MockitoExtension.class)
public class PersonControllerTest {

	//Prepare the environment with all the needed instances
	@InjectMocks
	private PersonController personController;

	@Mock
	private PersonService personService;

	private List<Person> list;
	
	//Before every test, we simulate data to check the correct functioning of the classes
	@BeforeEach
	public void setup() {
		list = List.of(Person.builder().id(1).name("Brayan").age(22).dateJoined("2020-21-08").gender("M")
				.telephone(6465).address("1")
				.addresses(
						List.of(Address.builder().id(1).street("Morelos").number(2).city("Ponci").zipcode(45).build()))
				.build());
	}

	//This test check the function that return all the persons in the DB
	@Test
	public void getAllPersonsTest() {
		when(personService.getAllPersons()).thenReturn(list);
		assertEquals(1, personController.getAllPersons().size());
	}

	//Test to check if given an id the persons exist, and return it
	@Test
	public void getPersonByIdTest() {
		final Person person = list.get(0);
		when(personService.getPersonById(1)).thenReturn(person);
		assertEquals(person, personController.getPersonById(1));
	}

	//Test to check the correct functioning to save a new person id the DB
	@Test
	public void addNewPersonTest() {
		final Person person = list.get(0);
		when(personService.addNewPerson(person)).thenReturn(person);
		assertEquals(person, personController.addNewPerson(person));
	}

	//Test to check the correct functioning to modify an existing person
	@Test
	public void modifyPersonTest() {
		final Person person = list.get(0);
		when(personService.addNewPerson(person)).thenReturn(person);
		assertEquals(person, personController.modifyPerson(person));
	}

	//Test to check if given an id, the person is deleted from the DB
	@Test
	public void deletePersonByIdTest() {
		personController.deletePersonById(1);
		verify(personService, times(1)).deletePersonById(1);
	}
	
	//Test to check if the custom exception is throwable when trying to search for a person that doesn't exist
	@Test
	public void findByIdTest () {
		when(personService.getPersonById(6)).thenThrow(NotFoundexception.class);
		assertThrows(NotFoundexception.class, () -> personController.getPersonById(6));
	}
}
