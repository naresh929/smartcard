package com.sapient.smartcard.beans;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Exception response Class
 * @author narkumar8
 *
 */
@Data
@AllArgsConstructor
public class ExceptionResponse {

	private String message;
	private String details;
}