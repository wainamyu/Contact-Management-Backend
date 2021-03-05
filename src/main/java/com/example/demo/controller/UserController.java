package com.example.demo.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.User;
import com.example.demo.dao.UserDao;
import com.example.demo.security.impl.AuthenticationSuccessResponse;
import com.example.demo.security.impl.Response;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("User")
public class UserController {

	@Autowired
	UserService userService;
	
	/**
	* Method to register user
	*
	* @param  user object
	* @return response with the user object created
	*/
	@PostMapping
	public Response register(@RequestBody User user) {
		user = userService.register(user);
		Response response = new AuthenticationSuccessResponse(true, HttpServletResponse.SC_OK, "Login sssuccessfully.", user);
		return response;
	}
	
}
