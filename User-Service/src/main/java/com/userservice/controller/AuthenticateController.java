package com.userservice.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userservice.model.AuthenticationRequest;
import com.userservice.model.AuthenticationResponse;
import com.userservice.model.User;
import com.userservice.service.UserService;
import com.userservice.util.JwtUtil;

@RestController
@CrossOrigin("*")
public class AuthenticateController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	
	//generatetoken
	
	@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken(@RequestBody AuthenticationRequest request) throws Exception{
			
		try {
			
			authenticate(request.getUsername(), request.getPassword());
			
		}catch(UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("User not Found");
		}
		
		//-------authenticate user
		
		UserDetails userDetails =userService.loadUserByUsername(request.getUsername());
		String token= jwtUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthenticationResponse(token));
	}
	
	
	
	private void authenticate(String username, String password) throws Exception
	{
		try {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		}
		catch(BadCredentialsException e)
		{
			throw new Exception("Invalid Creditial" + e.getMessage());
		}
	}
	
	//returns the details of current user
	@GetMapping("/current-user")
	public User getCurrentUser(Principal principal)
	{
		return ((User) this.userService.loadUserByUsername(principal.getName()));
	}
}
