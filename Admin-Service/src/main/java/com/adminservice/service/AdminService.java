package com.adminservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.adminservice.custom.exception.AdminNotFound;
import com.adminservice.model.Admin;
import com.adminservice.repository.AdminRepository;


@Service
public class AdminService implements UserDetailsService{
	
	@Autowired
	private AdminRepository adminRepository;
	
	// save admin details
	public int saveAdmin(Admin admin)
	{
		int id = adminRepository.save(admin).getId();
		return id ;
	}
	
	//get all admin details
	public List<Admin> getAllAdmin()
	{
		List<Admin> list = adminRepository.findAll();
		return list;
	}
	
	//get admin details by id
	public Admin getAdminById(int id)
	{
		Optional<Admin> opt=  adminRepository.findById(id);
		Admin admin=opt.orElseThrow(()-> new AdminNotFound("Admin Not Found"));
		
		return admin;
	}
	
	//delete admin by id
	public void deleteAdmin(int id)
	{
		Admin admin = getAdminById(id);
		adminRepository.delete(admin);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Admin  admin= adminRepository.findByUsername(username);
		
		 if (admin == null) 
			{
			 	throw new AdminNotFound("No user Found !!");
			}
	
				
		return admin;
	}
	
}
