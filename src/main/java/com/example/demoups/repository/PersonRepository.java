package com.example.demoups.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demoups.model.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer>{

	//This is the repository to control and manipulate the data for the persons object
}
