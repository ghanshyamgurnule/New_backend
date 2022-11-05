package com.cartservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cartservice.model.Cart;
import com.cartservice.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;
	
			
	@GetMapping("/hello")
	public String homepage() {
		return "Hello (Cart)";
	}
	
	//------- add to cart
	@PostMapping("/addCart")
	public ResponseEntity<String> saveCoupon(@RequestBody Cart cart)
	{
		int id= cartService.saveCoupon(cart);
		return new ResponseEntity<String>("Coupon '"+id+"' saved", HttpStatus.OK); 
	}
	
	//------- fetch coupon from cart 
	@GetMapping("/getCart")
	public ResponseEntity<List<Cart>> getCart()
	{
		List<Cart> list=cartService.getCart();
		return new ResponseEntity<List<Cart>>(list, HttpStatus.OK); 
	}
	
	//------- fetch cart by userid 
	@GetMapping("/getCartByUserId/{userId}")
	public ResponseEntity<Cart> getCartByUserId(@PathVariable int userId)
	{
		Cart cart=cartService.getCartByUserId(userId);
		return new ResponseEntity<Cart>(cart, HttpStatus.OK); 
		
	}
	
	//-------update cart by cartid
	@PutMapping("/updateCart/{cartId}")
	public ResponseEntity<String> updateCart(@RequestBody Cart cart, @PathVariable int cartId) {
		 	Cart cart2=cartService.getCartById(cartId);	
		 	
		 	cart2.setCouponList(cart.getCouponList());
		 	
		 	cartService.saveCoupon(cart2);
		 	
		 	return new ResponseEntity<String>("Coupon added", HttpStatus.OK); 
		 	
	}
	
	//-------delete cart by cartid
	@DeleteMapping("/delete/{cartId}")
	public ResponseEntity<String> deleteCart(@PathVariable int cartId)
	{
		cartService.deleteCart(cartId);
		return new ResponseEntity<String>("Cart '"+ cartId+"' deleted", HttpStatus.OK);
	}
	
}
