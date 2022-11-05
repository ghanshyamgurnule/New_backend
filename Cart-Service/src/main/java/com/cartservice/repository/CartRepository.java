package com.cartservice.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cartservice.model.Cart;

public interface CartRepository extends MongoRepository<Cart, Integer>{

	public Optional<Cart> findByUserId(int userId);



}
