package com.todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.todolist.repository.UserRepository;

import static java.util.Collections.emptyList;

import java.util.ArrayList;
import java.util.Collection;


@Service
public class ApplicationUserDetailsService implements UserDetailsService {
	
	@Autowired
    private UserRepository userRepository;
    

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ArrayList<GrantedAuthority> newAuthorities = new ArrayList<GrantedAuthority>();
		
		//extracting username from String 'username' which is in format '{sub='username',exp='number'}
		String[] array=username.split("=");
		if(array.length>1)
			username=array[1].split(",")[0];
		
		// getting the user details corresponding to the username
    	com.todolist.model.User applicationUser = userRepository.getUserName(username);
    	
        if(applicationUser == null)
        	throw new UsernameNotFoundException("User name not found !!!");
        
        return new User(applicationUser.getUserName(), applicationUser.getPassword(),newAuthorities);
    }
}
