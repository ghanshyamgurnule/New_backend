package com.userservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.userservice.custom.exception.UserNotFound;
import com.userservice.model.User;
import com.userservice.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{

	
	@Autowired
	private UserRepository userRepository;
	
	//save user
	public int saveUser(User user)
	{
		int id = userRepository.save(user).getId();
		return id ;
	}
	
	//get all the user details
	public List<User> getAllUser()
	{
		List<User> list = userRepository.findAll();
		return list;
	}
	
	//get user details by id
	public User getUserById(int id)
	{
		Optional<User> opt=  userRepository.findById(id);
		User user=opt.orElseThrow(()-> new UserNotFound("User Not Found"));
		
		return user;
	}
	
	//delete user by id
	public void deleteUser(int id)
	{
		User user = getUserById(id);
		userRepository.delete(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User  user= userRepository.findByUsername(username);
		
		 if (user == null) 
			{
			 	throw new UserNotFound("No user Found !!");
			}
	
				
		return user;
	}
	

}
