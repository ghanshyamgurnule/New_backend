package com.userservice.model;

import java.util.Set;

public class Cart {

	private int cartId;
	private int userId;
	private Set<Coupon> couponList;
	
	public Cart()
	{
		
	}
	
	public Cart(int cartId, int userId, Set<Coupon> couponList) {
		super();
		this.cartId = cartId;
		this.userId = userId;
		this.couponList = couponList;
	}

	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Set<Coupon> getCouponList() {
		return couponList;
	}

	public void setCouponList(Set<Coupon> couponList) {
		this.couponList = couponList;
	}
	
	
	
}
