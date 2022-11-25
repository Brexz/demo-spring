package com.example.demoups;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import com.example.demoups.model.Address;
import com.example.demoups.service.AddressService;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {

	//Prepare the necessary instances to test
	@InjectMocks
	private AddressService addressService;

	@Mock
	private RestTemplate restTemplate;

	//Couple  variables we need
	private List<Address> list;
	//This is  the direction to where we need to go and consume the data
	private final String url = "http://localhost:8010/api";

	//Before every test, we need to configure the data to simulate and test the methods
	@BeforeEach
	public void setup() {
		list = List.of(Address.builder().id(1).street("Morelos").number(2).city("Ponci").zipcode(45).build());
	}

	//Test to check if the external service is retrieving the data we need
	@Test
	public void getAddressTest() {
		when(restTemplate.getForObject(url.concat("/getAddress/" + "1"), Address.class)).thenReturn(list.get(0));
		assertEquals(list.get(0), addressService.getAddress("1"));
	}

	//Test to check if the external service is retrieving the list of all addresses
	@Test
	public void getAllAddressesTest() {
		final Object[] arr = list.toArray();
		when(restTemplate.getForObject(url.concat("/getAllAddresses"), Address[].class))
				.thenReturn((Address[]) arr);
		assertEquals(list, addressService.getAllAddresses());
	}
}
