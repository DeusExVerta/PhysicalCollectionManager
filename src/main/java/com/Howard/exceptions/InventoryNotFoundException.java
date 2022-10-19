package com.Howard.exceptions;

public class InventoryNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5582818363461611790L;

	public InventoryNotFoundException(String message) 
	{
		super(message);
	}
}
