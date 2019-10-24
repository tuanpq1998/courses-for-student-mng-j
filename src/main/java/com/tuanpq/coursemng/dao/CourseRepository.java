/**
 * @author admin
 * @date 04-10-2019
 */

package com.tuanpq.coursemng.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tuanpq.coursemng.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {

	public Course findByName(String name);
}
