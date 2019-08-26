/**
 * 
 */
package com.sapient.smartcard.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sapient.smartcard.beans.ExceptionResponse;

/**
 * Global Exception Handler
 * @author narkumar8
 *
 */
@RestControllerAdvice
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {
	
	
	@ExceptionHandler(CardNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleCardNotFoundException(CardNotFoundException ex,
			WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(MinimumBalanceException.class)
	public final ResponseEntity<ExceptionResponse> handleMinimumBalanceException(MinimumBalanceException ex,
			WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse,HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(InvalidSwipeException.class)
	public final ResponseEntity<ExceptionResponse> handleInvalidSwipeException(InvalidSwipeException ex,
			WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(StationNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleStationNotFoundException(StationNotFoundException ex,
			WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(),
	      request.getDescription(false));
	  return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
