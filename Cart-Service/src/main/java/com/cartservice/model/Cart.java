package com.cartservice.model;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "CartDB")
public class Cart {

		@Id
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
