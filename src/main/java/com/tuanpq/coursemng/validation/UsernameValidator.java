/**
 * @author admin
 * @date 06-10-2019
 */

package com.tuanpq.coursemng.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.tuanpq.coursemng.service.UserService;


public class UsernameValidator implements ConstraintValidator<UniqueUsername, String> {

	@Autowired
	private UserService userService;
	
	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {
		return !userService.isUsernameExisted(username);
	}

}
