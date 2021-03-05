package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.bean.User;
import com.example.demo.dao.UserDao;

@Service
public class UserService {

	
	@Autowired
	UserDao userDao;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	
	
	public List<User> getUser(){
		return userDao.findAll();
	}
	
	/**
	* Method to register user
	*
	* @param  user object
	* @return created user
	*/
	public User register(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userDao.save(user);
		return user;
	}
	
	
}
