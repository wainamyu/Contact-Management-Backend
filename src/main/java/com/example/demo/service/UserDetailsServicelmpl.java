package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserDao;

@Service
public class UserDetailsServicelmpl implements UserDetailsService{
	 
	
	@Autowired
	UserDao userDao;
	
	
	/**
	* custom userDetails service implementation, it will validate against data in database
	*
	*/
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userDao.findByUsername(username);
	}
	
}
