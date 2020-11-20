package com.todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.todolist.repository.UserRepository;

import static java.util.Collections.emptyList;


@Service
public class ApplicationUserDetailsService implements UserDetailsService {
	
	@Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	com.todolist.model.User applicationUser = userRepository.getUserName(username);
        if(applicationUser == null)
        	throw new UsernameNotFoundException("User name not found !!!");
        return new User(applicationUser.getUserName(), applicationUser.getPassword(), emptyList());
    }
}
