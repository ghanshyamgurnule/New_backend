package com.couponservice.custom.exception;

public class CouponNotFound extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CouponNotFound() {
		super();
	}
	
	public CouponNotFound(String msg)
	{
		super(msg);
	}

}
