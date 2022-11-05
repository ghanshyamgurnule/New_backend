package com.adminservice.Controller;

import java.util.Arrays;
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

import com.adminservice.model.Admin;
import com.adminservice.model.Coupon;
import com.adminservice.model.User;
import com.adminservice.service.AdminService;


@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {
	
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	@GetMapping("/home")
	public String homepage()
	{
		return "HelloWorld Admin";
	}
	
		//-------add admin details
			
			@PostMapping("/saveAdmin")
			public ResponseEntity<String> addAdmin(@RequestBody Admin admin)
			{
				int id = adminService.saveAdmin(admin);
				
				return  new ResponseEntity<String>("Employee '"+id+"' saved", HttpStatus.OK);
			}
		
		//------- fetch all admin details
			
			@GetMapping("/getAdmin")
			public  ResponseEntity<List<Admin>> getAllAdmin()
			{
				List<Admin> list= adminService.getAllAdmin();
				return new ResponseEntity<List<Admin>>(list, HttpStatus.OK); 
			}
		
		//-------fetch admin details by id
			
			@GetMapping("/getAdminById/{id}")
			public  ResponseEntity<Admin> getAdminById(@PathVariable int id)
			{
				Admin admin= adminService.getAdminById(id);
				return new ResponseEntity<Admin>(admin, HttpStatus.OK); 
			}
		
			
		//-------update admin details by id
			
			@PutMapping("/update/{id}")
			public ResponseEntity<String> updateAdmin(@RequestBody Admin admin,@PathVariable int id) 
			{
				Admin admin2=adminService.getAdminById(id);
				
				 admin2.setName(admin.getName());
				 admin2.setUsername(admin.getUsername());
				 admin2.setEmail(admin.getEmail());
				 admin2.setPassword(admin.getPassword());
				 
				 adminService.saveAdmin(admin2);
			 
				 return new ResponseEntity<String>("Admin '"+ id+"' Updated", HttpStatus.OK); 
		
				 
			}
			
			
	//-------delete user details by id
			
			@DeleteMapping("/delete/{id}")
			public ResponseEntity<String> deleteAdmin(@PathVariable int id)
			{
				adminService.deleteAdmin(id);
				 return new ResponseEntity<String>("Admin '"+ id+"' deleted", HttpStatus.OK);
			}
	
	
//communication with coupon microservice using RestTemplate
	
	//------- fetch all coupon 
			@GetMapping("/getCoupon")
			public List<Coupon> getCoupon()
			{
				String url="http://Coupon-Service/coupon/getAllCoupon";
				return restTemplate.getForObject(url, List.class);
			}
	
	//------- fetch coupon by id
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
	
	//------- add coupon
			@PostMapping("/addCoupon")
			public String addCoupon(@RequestBody Coupon coupon) 
			{
				restTemplate.postForObject("http://Coupon-Service/coupon/addCoupon", coupon, String.class);
				return "Coupon added";
			}
	
	//-------update coupon details by id
			@PutMapping("/updateCoupon/{id}")
			public String updateCouponById(@PathVariable int id, @RequestBody Coupon coupon) {
				restTemplate.put("http://Coupon-Service/coupon/updateCoupon/"+id, coupon, String.class);
				return "Coupon Updated";
			}
	
	//-------delete coupon by id
			@DeleteMapping("/deleteCoupon/{id}")
			public String deleteCouponById(@PathVariable int id) 
			{
				restTemplate.delete("http://Coupon-Service/coupon/delete/"+id, String.class);
				return "Coupon Deleted";
			}
			
			

//communication with User microservice using RestTemplate

	
	//------- fetch all users details 
			@GetMapping("/getUser")
			public List<User> getAllUser()
			{
					return Arrays.asList(restTemplate.getForObject("http://User-Service/user/getUser", User[].class));
			}
	
	//-------fetch user details by id
			@GetMapping("/getUser/{id}")
			public User getUserById(@PathVariable int id )
			{
				return restTemplate.getForObject("http://User-Service/user/getUserById/" + id, User.class);
			}
	
	//------- delete user  by id
			@DeleteMapping("/deleteUser/{id}")
			public String deleteUsernById(@PathVariable int id) 
			{
				restTemplate.delete("http://User-Service/user/delete/"+id, String.class);
				return "User Deleted";
			}
	
}
