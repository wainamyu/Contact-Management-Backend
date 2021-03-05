package com.example.demo.security;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.GrantedAuthority;

import com.example.demo.bean.User;
import com.example.demo.security.impl.AuthenticationSuccessResponse;
import com.example.demo.security.impl.Response;
import com.fasterxml.jackson.databind.ObjectMapper;


public class SecurityUtils {

	private static final ObjectMapper mapper = new ObjectMapper();

	public static void sendResponse(HttpServletResponse httpServletResponse, int status, String message, Exception exception)
			throws IOException {
		Response response = new Response(exception == null ? true : false, status, message);
		flushResponse(httpServletResponse, response);
	}
	
	
	//method is called upon successful authentication
	public static void sendAuthenticationSuccessResponse(HttpServletResponse httpServletResponse, int status, String message, Exception exception, User user)
			throws IOException {
		user.setPassword(null);
		user.setUsername(null);
		Response response = new AuthenticationSuccessResponse(exception == null ? true : false, status, message, user);
		flushResponse(httpServletResponse, response);
	}
	
	//method to wrap up response
	public static void flushResponse(HttpServletResponse httpServletResponse, Response response) throws IOException {
		httpServletResponse.setContentType("application/json;charset=UTF-8");
		httpServletResponse.setStatus(response.getCode());
		PrintWriter writer = httpServletResponse.getWriter();
		writer.write(mapper.writeValueAsString(response));
		writer.flush();
		writer.close();
	}
	
	
	/**
	* Method to check the role is a user
	*
	* @param  user profiles which is from database
	* @return true or false
	*/
	public static boolean isAdmin(Collection<? extends GrantedAuthority> profiles) {
		boolean isAdmin = false;
		for(GrantedAuthority profle: profiles) {
			if(profle.getAuthority().equals("ROLE_ADMIN")) {
				isAdmin = true;
			}
		};
		return isAdmin;
	}

}


