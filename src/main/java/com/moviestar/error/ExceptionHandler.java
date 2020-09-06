package com.moviestar.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.moviestar.models.ApiException;

import javassist.NotFoundException;


@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
	
	@org.springframework.web.bind.annotation.ExceptionHandler(value= {IllegalArgumentException.class})
	protected ResponseEntity<Object> handleBadRequestError(
			IllegalArgumentException ex, WebRequest request) {
		ApiException exceptionBody = new ApiException();
		exceptionBody.setCode(HttpStatus.BAD_REQUEST.getReasonPhrase());
		exceptionBody.setMessage(ex.getMessage());
		return new ResponseEntity<>(exceptionBody, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(value= {ResourceNotFoundException.class})
	protected ResponseEntity<Object> handleNotFoundError(
			ResourceNotFoundException ex, WebRequest request) {
		ApiException exceptionBody = new ApiException();
		exceptionBody.setCode(HttpStatus.NOT_FOUND.getReasonPhrase());
		exceptionBody.setMessage(ex.getMessage());
		return new ResponseEntity<>(exceptionBody, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(value= {Exception.class})
	protected ResponseEntity<Object> handleInternalServerError(
			RuntimeException ex, WebRequest request) {
		ApiException exceptionBody = new ApiException();
		exceptionBody.setCode(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		exceptionBody.setMessage(ex.getMessage());
		return new ResponseEntity<>(exceptionBody, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}