package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entities.Song;
import com.example.demo.entities.Users;
import com.example.demo.services.SongService;
import com.example.demo.services.UserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;




@Controller
public class UserController {
	@Autowired
	UserService us;
	
	@Autowired
	SongService ss;
	public UserController(UserService us) {
		super();
		this.us = us;
	}


	@PostMapping("/register")
	public String addUser(@ModelAttribute Users user) {
		
		boolean userStatus=us.emailExists(user.getEmail());
		if(userStatus==false) {
		us.addUsers(user);
		System.out.println("user added ");
		}else {
			System.out.println("user already exist");
		}
				
		return "login";
	}
	
	@PostMapping("/validate")
	public String login(@RequestParam("email")String email,@RequestParam("password")String password,HttpSession session,Model model) {
		if(us.validateUser(email,password)==true) {
			String role=us.getRole(email);
			
			session.setAttribute("email", email);
			if(role.equals("admin")) {
				return "adminHome";
			}else {
				Users user=us.getUser(email);
				boolean userStatus=user.isPremium();
				List<Song> songsList=ss.fetchAllSongs();
				model.addAttribute("songs", songsList);
				model.addAttribute("isPremium", userStatus);
				return "coustomerHome";
			}
		}else {
			return "login";
		}
	}
	
	
//	@GetMapping("/pay")
//	public String pay(@RequestParam String email) {
//		boolean paymentStatus=false;
//		if(paymentStatus==true) {
//			Users user=us.getUser(email);
//			user.setPremium(true);
//			us.updateUser(user);
//		}
//		return "login";
//	}
	
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
	}
	
	
	
}











