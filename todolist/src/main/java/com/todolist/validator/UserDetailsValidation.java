package com.todolist.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;


@Component
public class UserDetailsValidation {
	
	public boolean validatePassword(String password) {
		
		//Pattern p=Pattern.compile("a");
		//Matcher m=p.matcher(password);
		
		//Boolean result=m.matches()==true?true:false;
		
		return true;
	}

}
