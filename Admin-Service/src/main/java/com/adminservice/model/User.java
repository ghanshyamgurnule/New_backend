package com.adminservice.model;


public class User {
	
	private int id;
	private String name;
	private String email;
	private String username;
	private int password;
	private String city;
	private String gender;
	
	public User() {
		
	}
	
	public User(int id, String name, String email,String username, int password, String city, String gender) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.username=username;
		this.password = password;
		this.city = city;
		this.gender = gender;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getPassword() {
		return password;
	}
	public void setPassword(int password) {
		this.password = password;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
		
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
}
