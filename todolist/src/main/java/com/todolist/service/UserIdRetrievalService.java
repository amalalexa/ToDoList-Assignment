package com.todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.todolist.model.User;
import com.todolist.repository.UserRepository;

@Service
public class UserIdRetrievalService {

	@Autowired
	private UserRepository userRepository;

	public User Getloggedinuserdetails()
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username=(String)auth.getName();
		String[] array=username.split("=");
		username=array[1].split(",")[0];
		User userdetails=userRepository.getUserName(username);
		return userdetails;
	}
}
