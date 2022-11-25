package com.example.demoups;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import com.example.demoups.model.Address;
import com.example.demoups.model.Person;
import com.example.demoups.repository.PersonRepository;
import com.example.demoups.service.AddressService;
import com.example.demoups.service.PersonService;

@ExtendWith(MockitoExtension.class)
class DemoUpsApplicationTests {

	//Prepare the necessary instances to test
	@InjectMocks
	private PersonService personService;

	@Mock
	private PersonRepository personRepository;

	@Mock
	private AddressService addressService;

	@Mock
	private RestTemplate restTemplate;

	private List<Person> list;

	//Before every test, we need to configure the data to simulate and test the methods
	@BeforeEach
	public void setup() {
		list = List.of(Person.builder().id(1).name("Brayan").age(22).dateJoined("2020-21-08").gender("M")
				.telephone(6465).address("1").addresses(
						List.of(Address.builder().id(1).street("Morelos").number(2).city("Ponci").zipcode(45).build()))
				.build());
	}

	//Test to check if the service is given all the persons correctly
	@Test
	public void getAllPersonsTest() {
		when(personRepository.findAll()).thenReturn(list);
		assertEquals(1, personService.getAllPersons().size());
	}

	//Test to check if the service is given and consuming in a correct way the data
	@Test
	public void getPersonTest() {
		final Person person = list.get(0);
		when(personRepository.findById(1)).thenReturn(Optional.of(person));
		when(addressService.getAddress("1")).thenReturn(person.getAddresses().get(0));
		assertEquals(person, personService.getPersonById(1));
	}

	//Test to check if a new person is added correctly 
	@Test
	public void addNewPersonTest() {
		final Person person = list.get(0);
		when(personRepository.save(person)).thenReturn(person);
		assertEquals(person, personService.addNewPerson(person));
	}

	//Test to check if a person is deleted correctly
	@Test
	public void deletePersonTest() {
		personService.deletePersonById(1);
		verify(personRepository, times(1)).deleteById(1);
	}
	
	
	
}
