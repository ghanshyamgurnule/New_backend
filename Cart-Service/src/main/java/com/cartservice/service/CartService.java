package com.cartservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.cartservice.custom.exception.CartNotFound;
import com.cartservice.model.Cart;
import com.cartservice.repository.CartRepository;


@Service
public class CartService {
	
	@Autowired
	private CartRepository cartRepository;
	
	//------- add to cart
		public int saveCoupon( Cart cart){
			int id=cartRepository.save(cart).getCartId();
			return id;
		}
		
		//------- fetch coupon from cart 
		public List<Cart> getCart(){
			List<Cart> list= cartRepository.findAll(); 
			return list;
		}
		//----------fetch cart by cart id
		public Cart getCartById( int cartId)
		{
			Optional<Cart> opt=  cartRepository.findById(cartId);
			Cart cart=opt.orElseThrow(()-> new CartNotFound("Cart Not Found"));
			
			return cart;
		}
		
		//------- fetch cart by userid 
		public Cart getCartByUserId( int userId)
		{
			Optional<Cart> opt=  cartRepository.findByUserId(userId);
			Cart cart=opt.orElseThrow(()-> new CartNotFound("Cart Not Found"));
			
			return cart;
		}
		
		
		
		//-------delete cart by cartid
		public void deleteCart( int cartId)
		{
			Cart cart = getCartById(cartId);
			cartRepository.delete(cart);
		}

}
