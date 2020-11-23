package com.todolist.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.todolist.constants.SecurityConstants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class AuthorizationFilter extends BasicAuthenticationFilter {

    public AuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
    	//receives the token from the request header where the header name "Authorization"
        String header = request.getHeader(SecurityConstants.HEADER_NAME);
        if (header == null) {
            chain.doFilter(request, response);
            return;
        }else {
        	// extract token where header variable is of the form "Bearer token"
        	header=header.split(" ")[1];
        }
        
        //authenticating user details
        UsernamePasswordAuthenticationToken authentication = authenticate(request);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken authenticate(HttpServletRequest request) {
    	
    	//extracting the token from request header
        String token = request.getHeader(SecurityConstants.HEADER_NAME);
        token=token.split(" ")[1];
        
        if (token != null) {
            Claims user = Jwts.parser()
                    .setSigningKey(Keys.hmacShaKeyFor(SecurityConstants.KEY.getBytes()))
                    .parseClaimsJws(token)
                    .getBody();

            if (user != null) {
            	//returning the user details from the token
                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList());
            }else{
                return  null;
            }

        }
        return null;
    }
}
