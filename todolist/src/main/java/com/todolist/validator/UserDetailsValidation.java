package com.todolist.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.todolist.repository.UserRepository;


@Component
public class UserDetailsValidation {
	
	@Autowired
	private UserRepository userRepository;
	
	public boolean validateUserName(String username) {
		
		if(userRepository.checkUserNameExsists(username)==1)
			return false;
		else
			return true;
	}

}
