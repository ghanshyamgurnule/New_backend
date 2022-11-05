package com.couponservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.couponservice.model.Coupon;
public interface CouponRepository extends MongoRepository<Coupon, Integer>{
	
	public Coupon findCouponById(int id);
	
	public List<Coupon> findByCategory(String category);

}
