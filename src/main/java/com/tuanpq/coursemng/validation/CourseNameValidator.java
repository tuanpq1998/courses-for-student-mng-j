/**
 * @author admin
 * @date 06-10-2019
 */

package com.tuanpq.coursemng.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.tuanpq.coursemng.service.CourseService;


public class CourseNameValidator implements ConstraintValidator<UniqueCourseName, String> {

	@Autowired
	private CourseService courseService;
	
	@Override
	public boolean isValid(String courseName, ConstraintValidatorContext context) {
		return !courseService.isCourseNameExisted(courseName);
	}

}
