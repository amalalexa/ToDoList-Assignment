package com.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todolist.configuration.SecurityConfiguration;
import com.todolist.repository.UserRepository;
import com.todolist.service.UserService;
import com.todolist.view.LoginDetailsView;
import com.todolist.view.SignUpView;

@CrossOrigin(origins="*")
@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/user/signup")
	public ResponseEntity<String> signingUpUser(@RequestBody SignUpView signUpView){
		try {
			return ResponseEntity.ok().body(userService.signUpUser(signUpView));
		}catch(Exception e) {
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}
	
}
