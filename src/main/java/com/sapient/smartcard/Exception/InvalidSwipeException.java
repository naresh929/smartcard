/**
 * 
 */
package com.sapient.smartcard.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Invalid Swipe Custom Exception
 * @author narkumar8
 *
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidSwipeException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidSwipeException(String message) {
		super(message);
	}

	public InvalidSwipeException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidSwipeException() {
		super();
	}
}
