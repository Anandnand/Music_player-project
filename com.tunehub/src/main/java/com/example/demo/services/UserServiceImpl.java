package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Users;
import com.example.demo.repositories.UserRepo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepo up;

	public UserServiceImpl(UserRepo up) {
		super();
		this.up = up;
	}

	@Override
	public String addUsers(Users user) {
		up.save(user);
		return "user added successfully";
	}

	@Override
	public boolean emailExists(String email) {
		if (up.findByEmail(email) != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean validateUser(String email, String password) {
		Users user = up.findByEmail(email);
		String db_pass = user.getPassword();
		if (password.equals(db_pass)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String getRole(String email) {
		Users user=up.findByEmail(email);
		return 	user.getRole();

	}

	@Override
	public Users getUser(String email) {
		return  up.findByEmail(email);
	}

	@Override
	public void updateUser(Users user) {
		up.save(user);		
	}

}
