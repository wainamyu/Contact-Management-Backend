package com.example.demo.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.demo.security.impl.AccessDeniedHandlerImpl;
import com.example.demo.security.impl.AuthenticationEntryPointImpl;
import com.example.demo.security.impl.AuthenticationFailureHandlerImpl;
import com.example.demo.security.impl.AuthenticationSuccessHandlerImpl;
import com.example.demo.security.impl.LogoutSuccessHandlerImpl;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	AuthenticationSuccessHandlerImpl authenticationSuccessHandlerImpl;
	
	@Autowired
	AuthenticationFailureHandlerImpl authenticationFailureHandlerImpl;
	
	@Autowired
	AuthenticationEntryPointImpl authenticationEntryPointImpl;
	
	@Autowired
	AccessDeniedHandlerImpl accessDeniedHandlerImpl;
	
	@Autowired
	LogoutSuccessHandlerImpl logoutSuccessHandlerImpl;
	
	
	/**
	* Method to authenticate a user
	* Login end point is implemented by Spring, we can take advantage of spring security 
	*/
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.formLogin()
			.loginProcessingUrl("/login") 
			.usernameParameter("username")
			.passwordParameter("password")
			.successHandler(authenticationSuccessHandlerImpl)
			.failureHandler(authenticationFailureHandlerImpl);

		http.exceptionHandling().authenticationEntryPoint(authenticationEntryPointImpl);

		http.exceptionHandling().accessDeniedHandler(accessDeniedHandlerImpl);

		http.rememberMe();

		http.logout()
			.logoutUrl("/logout")
			.logoutSuccessHandler(logoutSuccessHandlerImpl);
		http.cors();
	}
	
	/**
	* Method to encode password
	* As required by spring security thus providing a password encoder
	*/
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(11);
	}
	
	
	/**
	* Method to indicate that we are using userDetailsService interface to authenticate a user
	*/
	@Autowired
	public void setup(AuthenticationManagerBuilder builder) throws Exception {
		builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	/**
	* Allowing all origin just for simplicity, so backend can receive request from frontend
	*/
	@Bean
    CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*"); 
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","HEAD","OPTIONS"));
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
	
}
