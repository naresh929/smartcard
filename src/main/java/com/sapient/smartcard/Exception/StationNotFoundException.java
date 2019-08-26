/**
 * 
 */
package com.sapient.smartcard.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Station Not Found Custom Exception
 * @author narkumar8
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class StationNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public StationNotFoundException(String message) {
		super(message);
	}

	public StationNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public StationNotFoundException() {
		super();
	}
}
