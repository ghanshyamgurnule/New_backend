package com.cartservice.custom.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice              // if restcontroller throw any exception  it will come here
public class CustomExceptionHandler {
	
	@ExceptionHandler(CartNotFound.class)     //if AdminNotFound exception occur call this method 
	public ResponseEntity<String> handleCartNotFound(CartNotFound canf){
		
		return new ResponseEntity<String>(canf.getMessage(), HttpStatus.NOT_FOUND);
		
	}

}
