package com.todolist.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.todolist.exception.PasswordException;
import com.todolist.model.User;
import com.todolist.repository.UserRepository;
import com.todolist.validator.UserDetailsValidation;
import com.todolist.view.SignUpView;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserDetailsValidation userValidation;
	
	public String signUpUser(SignUpView signUpView) {
		
		try {
			
			//validating the password
			if(!userValidation.validatePassword(signUpView.getPassword()))
				throw new PasswordException("Password not in a valid format !!!");
			
			User userObject=new User();
			BCryptPasswordEncoder bc=new BCryptPasswordEncoder();
			//copying values to userObject(to save)
			BeanUtils.copyProperties(signUpView, userObject);
			userObject.setPassword(bc.encode(signUpView.getPassword()));
			
			userRepository.save(userObject);
			
			return "User Successfully Registered !!!";
		}catch(PasswordException e) {
			return e.getMessage();
		}catch(Exception e) {
			return e.getMessage();
		}
	}
	
	public void logInUser() {
		
	}

}
