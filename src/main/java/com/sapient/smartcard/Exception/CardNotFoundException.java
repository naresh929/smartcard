/**
 * 
 */
package com.sapient.smartcard.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Card Not Found Custom Exception
 * @author narkumar8
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CardNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public CardNotFoundException(String message) {
		super(message);
	}

	public CardNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public CardNotFoundException() {
		super();
	}
}
