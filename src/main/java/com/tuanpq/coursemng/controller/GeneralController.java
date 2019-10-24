/**
 * @author admin
 * @date 05-10-2019
 */

package com.tuanpq.coursemng.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tuanpq.coursemng.entity.Course;
import com.tuanpq.coursemng.entity.Role;
import com.tuanpq.coursemng.entity.User;
import com.tuanpq.coursemng.service.CourseService;
import com.tuanpq.coursemng.service.UserService;
import com.tuanpq.coursemng.utility.ConstantDelivery;

@Controller
public class GeneralController {

	@Autowired
	private CourseService courseService;
	
	@Autowired
	private ConstantDelivery constantDelivery;
	
	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String show(){
		return "index";
	}
	
	@GetMapping(path = {"/course", "/course/"})
	public String showAllCourses(Model model, Authentication authentication) {
		List<Course> courses = courseService.findAll();
		List<Role> roles = (List<Role>) authentication.getAuthorities();
		User user = null;
		if (roles == null || roles.size() == 0 || 
				roles.get(0).getName().equals(constantDelivery.ADMIN_ROLE)){
		} else {
			user = userService.findByUsername(authentication.getName());
			Set<Course> studentCourses =  user.getCourses();
			model.addAttribute("studentCourses", studentCourses);
			
			//remove
			for (int i = courses.size()-1; i>=0; i--) {
				if(studentCourses.contains(courses.get(i)))
					courses.remove(i);
			}
		}
		model.addAttribute("courses", courses);
		return "course/list";
	}
	
}
