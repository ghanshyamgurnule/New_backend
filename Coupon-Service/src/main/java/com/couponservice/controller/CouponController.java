package com.couponservice.controller;

import java.util.List;
import java.util.Optional;

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

import com.couponservice.model.Coupon;
import com.couponservice.repository.CouponRepository;
import com.couponservice.service.CouponService;

@RestController
@RequestMapping("/coupon")
@CrossOrigin("*")
public class CouponController {
	
	@Autowired
	CouponService couponService;
	
	@GetMapping("/home")
	public String homepage()
	{
		return "HelloWorld";
	}
	
	//------- add coupon
			@PostMapping("/addCoupon")
			public ResponseEntity<String> addCoupon(@RequestBody Coupon coupon)
			{
				int id=couponService.saveCoupon(coupon);
				return new ResponseEntity<String>("Coupon '"+id+"' saved", HttpStatus.OK);			}
	
	//------- fetch all coupon 

			@GetMapping("/getAllCoupon")
			public ResponseEntity<List<Coupon>> getAllcoupon()
			{
				List<Coupon> list= couponService.getAllcoupon(); 
				return new ResponseEntity<List<Coupon>>(list, HttpStatus.OK);			

			}
	
	//------- fetch coupon by id

			@GetMapping("/getCouponById/{id}")
			public ResponseEntity<Coupon> getCouponById( @PathVariable int id)
			{
				 Coupon coupon= couponService.getCouponById(id); 
					return new ResponseEntity<Coupon>(coupon, HttpStatus.OK);			
			}
	
	//-------fetch coupon by category
			@GetMapping("/getCouponByCategory/{category}")
			public List<Coupon> getCouponByCategory( @PathVariable String category)
			{
				 return couponService.getCouponByCategory(category); 
			}
	
	//-------update coupon details by id
			@PutMapping("/updateCoupon/{id}")
			public ResponseEntity<String> updateCoupon(@RequestBody Coupon coupon, @PathVariable int id)
			{
				Coupon coupon2=couponService.getCouponById(id);
				
				coupon2.setCouponName(coupon.getCouponName());
				coupon2.setCategory(coupon.getCategory());
				coupon2.setDesc(coupon.getDesc());
				coupon2.setCouponCode(coupon.getCouponCode());
				
				couponService.saveCoupon(coupon2);
				return new ResponseEntity<String>("Coupon '"+ id+"' Updated", HttpStatus.OK); 
				
			}
	
	//-------delete coupon by id
			@DeleteMapping("/delete/{id}")
			public String deleteCoupon(@PathVariable int id)
			{
				couponService.deleteCoupon(id);
				return "Coupon Deleted";
			}
	
}
