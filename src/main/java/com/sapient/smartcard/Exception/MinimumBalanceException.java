/**
 * 
 */
package com.sapient.smartcard.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Minimum Balance Custom Exception
 * @author narkumar8
 *
 */
@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class MinimumBalanceException extends Exception {

	private static final long serialVersionUID = 1L;

	public MinimumBalanceException(String message) {
		super(message);
	}

	public MinimumBalanceException(String message, Throwable cause) {
		super(message, cause);
	}

	public MinimumBalanceException() {
		super();
	}
}
