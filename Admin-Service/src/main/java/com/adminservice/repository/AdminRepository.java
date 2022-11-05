package com.adminservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.adminservice.model.Admin;

public interface AdminRepository extends MongoRepository<Admin, Integer> {

	public List<Admin> findByName();

	public Admin findByUsername(String username);

}
