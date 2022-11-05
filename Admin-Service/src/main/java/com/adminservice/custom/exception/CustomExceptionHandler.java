package com.adminservice.custom.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice              // if restcontroller any exception thrown it will come here
public class CustomExceptionHandler {
	
	@ExceptionHandler(AdminNotFound.class)     //if AdminNotFound exception occur call this method 
	public ResponseEntity<String> handleAdminNotFound(AdminNotFound anf){
		
		return new ResponseEntity<String>(anf.getMessage(), HttpStatus.NOT_FOUND);
		
	}

}
