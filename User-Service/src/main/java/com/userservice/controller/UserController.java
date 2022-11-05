package com.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.userservice.model.Cart;
import com.userservice.model.Coupon;
import com.userservice.model.User;

import com.userservice.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private RestTemplate restTemplate;


			@GetMapping("/home")
			public String homepage()
			{
				return "HelloWorldUser";
			}
	
	//-------add user details
			
			@PostMapping("/saveUser")
			public ResponseEntity<String> addUser(@RequestBody User user)
			{
				int id = userService.saveUser(user);
				
				return  new ResponseEntity<String>("User '"+id+"' saved", HttpStatus.OK);
			}
	
	//------- fetch all user details
			
			@GetMapping("/getUser")
			public  ResponseEntity<List<User>> getAllUser()
			{
				List<User> list= userService.getAllUser();
				return new ResponseEntity<List<User>>(list, HttpStatus.OK); 
			}
	
	//-------fetch user details by id
			
			@GetMapping("/getUserById/{id}")
			public  ResponseEntity<User> getUserById(@PathVariable int id)
			{
				User user= userService.getUserById(id);
				return new ResponseEntity<User>(user, HttpStatus.OK); 
			}

			
	//-------update user details by id
			
			@PutMapping("/update/{id}")
			public ResponseEntity<String> updateUser(@RequestBody User user,@PathVariable int id) 
			{
				User user2=userService.getUserById(id);
				
				 user2.setName(user.getName());
				 user2.setEmail(user.getEmail());
				 user2.setPassword(user.getPassword());
				 user2.setUsername(user.getUsername());
				 user2.setCity(user.getCity()); 
				 user2.setGender(user2.getGender());
				 
				 userService.saveUser(user2);
			 
				 return new ResponseEntity<String>("User '"+ id+"' Updated", HttpStatus.OK); 
 
				 
			}

			
	//-------delete user details by id
			
			@DeleteMapping("/delete/{id}")
			public ResponseEntity<String> deleteUser(@PathVariable int id)
			{
				userService.deleteUser(id);
				 return new ResponseEntity<String>("User '"+ id+"' deleted", HttpStatus.OK);
			}

	
	
//------------communication with coupon microservice using RestTemplate
	
	
	
	//-------fetch all coupons
			
			@GetMapping("/getAllCoupon")
			public List getAllcoupon()
			{
				return restTemplate.getForObject("http://Coupon-Service/coupon/getAllCoupon", List.class );
			}
	
	//-------fetch coupon by id
			
			@GetMapping("/getCoupons/{id}")
			public Coupon getCouponById(@PathVariable int id )
			{
				return restTemplate.getForObject("http://Coupon-Service/coupon/getCouponById/" + id, Coupon.class);
			}
	
	//------- fetch coupon by category
			
			@GetMapping("/getCouponsCategory/{category}")
			public List<Coupon> getCouponByCategory(@PathVariable String category )
			{
					return restTemplate.getForObject("http://Coupon-Service/coupon/getCouponByCategory/" + category, List.class);
			}
		
		
//------------communication with cart microservice using RestTemplate

				
	//-------fetch cart by userId	
			
			 @GetMapping("/getCartByUserId/{userId}")
			 public Cart getCartByUserId(@PathVariable int userId)
			 {
				return restTemplate.getForObject("http://Cart-Service/cart/getCartByUserId/"+ userId ,Cart.class );
				 
			 }
			 
   //--------add to cart or update cart			 
			 @PutMapping("/updateCart/{cartId}")
			 public String addToCart(@RequestBody Cart cart,@PathVariable int cartId) {
				 
				 restTemplate.put("http://Cart-Service/cart/updateCart/"+cartId,cart, String.class);
				 
				 return "Added to cart";
			 }

}
