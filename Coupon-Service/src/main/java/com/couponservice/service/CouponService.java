package com.couponservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.couponservice.custom.exception.CouponNotFound;
import com.couponservice.model.Coupon;
import com.couponservice.repository.CouponRepository;

@Service
public class CouponService {
	
	@Autowired
	CouponRepository couponRepository;
	
	//------- add coupon
	
	public int saveCoupon( Coupon coupon)
	{
		int id =couponRepository.save(coupon).getId();
		return id; 
	}

//------- fetch all coupon 

	public List<Coupon> getAllcoupon()
	{
		List<Coupon> list = couponRepository.findAll();
		return list;	
	}

//------- fetch coupon by id

	public Coupon getCouponById(int id)
	{
		Optional<Coupon> opt=  couponRepository.findById(id);
		Coupon coupon=opt.orElseThrow(()-> new CouponNotFound("Coupon Not Found"));
		
		return coupon;
	}

//-------fetch coupon by category
	public List<Coupon> getCouponByCategory( String category)
	{
		List<Coupon> list= couponRepository.findByCategory(category); 
	       
		return list;
	}


//-------delete coupon by id
	public void deleteCoupon(int id)
	{
		Coupon coupon = getCouponById(id);
		couponRepository.delete(coupon);
	}

}
