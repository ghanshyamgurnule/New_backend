package com.cartservice.custom.exception;

public class CartNotFound extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CartNotFound() {
		super();
	}
	
	public CartNotFound(String msg)
	{
		super(msg);
	}

}
