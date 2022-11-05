package com.userservice.custom.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice              // if restcontroller any exception thrown it will come here
public class CustomExceptionHandler {
	
	@ExceptionHandler(UserNotFound.class)     //if UserNotFound exception occur call this method 
	public ResponseEntity<String> handleUserNotFound(UserNotFound unf){
		
		return new ResponseEntity<String>(unf.getMessage(), HttpStatus.NOT_FOUND);
		
	}

}
