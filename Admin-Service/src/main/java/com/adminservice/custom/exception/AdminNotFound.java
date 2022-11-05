package com.adminservice.custom.exception;

public class AdminNotFound extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AdminNotFound() {
		super();
	}
	
	public AdminNotFound(String msg)
	{
		super(msg);
	}

}
