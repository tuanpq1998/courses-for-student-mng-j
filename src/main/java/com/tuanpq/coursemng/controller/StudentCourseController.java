/**
 * @author admin
 * @date 04-10-2019
 */

package com.tuanpq.coursemng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tuanpq.coursemng.entity.Course;
import com.tuanpq.coursemng.entity.User;
import com.tuanpq.coursemng.service.CourseService;
import com.tuanpq.coursemng.service.UserService;

@Controller
@RequestMapping("/student")
public class StudentCourseController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CourseService courseService;
	
	@PostMapping("/join-course")
	public String joinCourse(@RequestParam("id") int courseId, 
			Authentication authentication){
		User user = userService.findByUsername(authentication.getName());
		Course course = courseService.findById(courseId);
		if (user.addCourse(course))
			userService.saveUser(user);
		return "redirect:/course";
	}
	
	@PostMapping("/leave-course")
	public String leaveCourse(@RequestParam("id") int courseId, 
			Authentication authentication){
		User user = userService.findByUsername(authentication.getName());
		Course course = courseService.findById(courseId);
		if (user.removeCourse(course))
			userService.saveUser(user);
		return "redirect:/course";
	}
}
