/**
 * @author admin
 * @date 04-10-2019
 */

package com.tuanpq.coursemng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tuanpq.coursemng.entity.Course;
import com.tuanpq.coursemng.service.CourseService;
import com.tuanpq.coursemng.validation.ValidationGroups.OnCreate;
import com.tuanpq.coursemng.validation.ValidationGroups.OnUpdate;

@Controller
@RequestMapping("/admin")
public class AdminCourseController {
	
	@Autowired
	private CourseService courseService;
	
	@GetMapping("/create-course")
	public String showCourseForm(Model model) {
		model.addAttribute("course", new Course());
		return "course/add-form";
	}
	
	@GetMapping("/view-course")
	public String showCourseDetail(@RequestParam("id") int courseId, Model model) {
		Course course = courseService.findById(courseId);
		model.addAttribute("users", course.getUsers());
		model.addAttribute("courseName", course.getName());
		return "course/list-user";
	}
	
	@PostMapping("/create-course")
	public String createCourse(@Validated(OnCreate.class) @ModelAttribute Course course,
			BindingResult result) {
		if (result.hasErrors())
			return "course/add-form";
		course.setId(0);
		courseService.save(course);
		return "redirect:/course";
	}
	
	@GetMapping("/edit-course")
	public String showCourseEdit(@RequestParam int id, Model model) {
		Course course = courseService.findById(id);
		model.addAttribute("course", course);
		return "course/edit-form";
	}
	
	@PostMapping("/edit-course")
	public String updateCourse(
			@Validated(OnUpdate.class) @ModelAttribute Course course, BindingResult result) {
		if (result.hasErrors())
			return "course/edit-form";
		courseService.save(course);
		return "redirect:/course/";
	}
	
	@PostMapping("/delete-course")
	public String deleteCourse(@RequestParam("id") int courseId) {
		Course course = courseService.findById(courseId);
		if (course != null)
			courseService.delete(course);
		return "redirect:/course/";
	}
}
