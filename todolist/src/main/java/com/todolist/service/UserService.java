package com.todolist.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.todolist.exception.PasswordException;
import com.todolist.exception.UserNameExsistsException;
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
			if(signUpView.getPassword()=="" || signUpView.getPassword()==null)
				throw new PasswordException("Password shouldn't be empty !!!");
			
			//check if user exsists or not
			if(!userValidation.validateUserName(signUpView.getName()))
				throw new UserNameExsistsException("User Already Exsists !!!");
			
			
			User userObject=new User();
			
			//password encoder
			BCryptPasswordEncoder bc=new BCryptPasswordEncoder();
			
			//copying values to userObject(to save)
			userObject.setUserName(signUpView.getName());
			
			//setting the encrypted password
			userObject.setPassword(bc.encode(signUpView.getPassword()));
			
			userRepository.save(userObject);
			
			return "User Successfully Registered !!!";
			
		}catch(PasswordException e) {
			return e.getMessage();
		}catch(UserNameExsistsException e) {
			return e.getMessage();
		}catch(Exception e) {
			return e.getMessage();
		}
	}
}
