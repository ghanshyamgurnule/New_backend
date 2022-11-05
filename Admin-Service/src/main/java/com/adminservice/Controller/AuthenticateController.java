package com.adminservice.Controller;

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

import com.adminservice.model.Admin;
import com.adminservice.model.AuthenticationRequest;
import com.adminservice.model.AuthenticationResponse;
import com.adminservice.service.AdminService;
import com.adminservice.util.JwtUtil;


@RestController
@CrossOrigin("*")
public class AuthenticateController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private AdminService adminService;
	
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
		
		UserDetails userDetails =adminService.loadUserByUsername(request.getUsername());
		String token= jwtUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthenticationResponse(token));
	}
	
	
	
	
	
	
	
	private void authenticate(String username, String password) throws Exception
	{
		try {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		}
		catch(DisabledException e) {
			throw new Exception("User disabled"+ e.getMessage());
		}catch(BadCredentialsException e)
		{
			throw new Exception("Invalid Creditial" + e.getMessage());
		}
	}

	//returns the details of current user
		@GetMapping("/current-admin")
		public Admin getCurrentUser(Principal principal)
		{
			return ((Admin) this.adminService.loadUserByUsername(principal.getName()));
		}
}
