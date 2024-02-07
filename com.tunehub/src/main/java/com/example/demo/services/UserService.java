package com.example.demo.services;

import java.util.List;

import com.example.demo.entities.Users;

public interface UserService {
 
	public String addUsers(Users user);
	public boolean emailExists(String email);
	public boolean validateUser(String email, String password);
	public String getRole(String email);
	public Users getUser(String email);
	public void updateUser(Users user);
}
