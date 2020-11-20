package com.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todolist.configuration.SecurityConfiguration;
import com.todolist.repository.UserRepository;
import com.todolist.view.LoginDetailsView;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	
	@PostMapping("/login")
	public ResponseEntity<String> loggingInUser(){
		
		return ResponseEntity.ok().body("User LOgged In");
	}
}
