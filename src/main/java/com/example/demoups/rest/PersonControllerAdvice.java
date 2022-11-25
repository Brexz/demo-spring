package com.example.demoups.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demoups.exceptions.IncorrectNameException;
import com.example.demoups.exceptions.NotFoundexception;

@RestControllerAdvice
public class PersonControllerAdvice {

	//Handler that controls when the custom exception will be throwable
	@ExceptionHandler(NotFoundexception.class)
	public ResponseEntity<Void> notFoundException(final NotFoundexception exception) {
		return ResponseEntity.noContent().build();
	}

	//Handler that controls when the custom exception will be throwable
	@ExceptionHandler(IncorrectNameException.class)
	public ResponseEntity<Void> IncorrectNameException(final IncorrectNameException exception) {
		return ResponseEntity.badRequest().build();
	}
}
