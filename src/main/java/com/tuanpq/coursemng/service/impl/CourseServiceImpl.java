/**
 * @author admin
 * @date 04-10-2019
 */

package com.tuanpq.coursemng.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuanpq.coursemng.dao.CourseRepository;
import com.tuanpq.coursemng.entity.Course;
import com.tuanpq.coursemng.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepository courseRepository; 
	
	@Override
	public List<Course> findAll() {
		return courseRepository.findAll();
	}

	@Override
	public void save(Course course) {
		courseRepository.save(course);
	}

	@Override
	public boolean isCourseNameExisted(String name) {
		Course course = courseRepository.findByName(name);
		return course != null;
	}

	@Override
	public Course findById(int id) {
		Optional<Course> result = courseRepository.findById(id);
		Course course = null;
		if (result.isPresent())
			course = result.get();
		else throw new RuntimeException("Did not find course id - " + id);
		return course;
	}

	@Override
	public Course findByName(String name) {
		return courseRepository.findByName(name);
	}

	@Override
	public void delete(Course course) {
		courseRepository.delete(course);
	}

}
