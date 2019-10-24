/**
 * @author admin
 * @date 03-10-2019
 */

package com.tuanpq.coursemng.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.tuanpq.coursemng.service.UserService;
import com.tuanpq.coursemng.utility.ConstantDelivery;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    private UserService userService;
	
	@Autowired
    private LoginSuccessHandler loginSuccessHandler;
	
	@Autowired
	ConstantDelivery constantDelivery;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userService);
		provider.setPasswordEncoder(passwordEncoder());
		auth.authenticationProvider(provider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/admin/**").hasRole(constantDelivery.ADMIN)
			.antMatchers("/student/**").hasRole(constantDelivery.STUDENT)
			.antMatchers("/course/**").hasAnyRole(constantDelivery.ADMIN, 
					constantDelivery.STUDENT)
			.antMatchers("/profile/**").hasAnyRole(constantDelivery.ADMIN, 
					constantDelivery.STUDENT)
			.and().formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/login")
			.successHandler(loginSuccessHandler).permitAll()
			.and().logout().permitAll()
			.and().exceptionHandling().accessDeniedPage("/error");
	}
	
}
