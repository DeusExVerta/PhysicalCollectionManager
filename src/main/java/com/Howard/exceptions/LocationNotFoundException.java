package com.Howard.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LocationNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9100848353246016949L;

	public LocationNotFoundException(String message) {
		super(message);
		log.error(message);
	}


}
