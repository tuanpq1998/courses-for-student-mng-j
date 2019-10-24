/**
 * @author admin
 * @date 04-10-2019
 */

package com.tuanpq.coursemng.service;

import java.util.List;

import com.tuanpq.coursemng.entity.Course;

public interface CourseService {

	public List<Course> findAll();

	public void save(Course course);
	
	public boolean isCourseNameExisted(String courseName);

	public Course findById(int id);

	public Course findByName(String name);

	public void delete(Course course);
}
