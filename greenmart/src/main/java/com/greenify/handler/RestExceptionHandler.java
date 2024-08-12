package com.greenify.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.greenify.exceptions.BusinessException;

@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<Object> handleBusinessException(BusinessException businessException){
		return new ResponseEntity<Object>(businessException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
}
