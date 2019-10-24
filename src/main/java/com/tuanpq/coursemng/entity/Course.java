/**
 * @author admin
 * @date 04-10-2019
 */

package com.tuanpq.coursemng.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.tuanpq.coursemng.validation.UniqueCourseName;
import com.tuanpq.coursemng.validation.ValidationGroups.OnCreate;
import com.tuanpq.coursemng.validation.ValidationGroups.OnUpdate;

@Entity
@Table(name="course")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@UniqueCourseName(message="Course name has already existed!",groups = OnCreate.class)
	@NotBlank(message="Course name required!", groups = {OnUpdate.class, OnCreate.class})
	@Column(name="name")
	private String name;

	@NotNull(message = "Credits required!", groups = {OnUpdate.class, OnCreate.class})
	@Min(value=1, message="Credits must be positive number!", 
		groups = {OnUpdate.class, OnCreate.class})
	@Column(name="credits")
	private Integer credits;
	
	@ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,
            CascadeType.PERSIST}, fetch = FetchType.LAZY)
	@JoinTable(name = "users_courses",
				joinColumns = @JoinColumn(name="course_id"),
				inverseJoinColumns = @JoinColumn(name="user_id"))
	private Set<User> users = new HashSet<>();

	public Course(int id, String name, int credits) {
		super();
		this.id = id;
		this.name = name;
		this.credits = credits;
	}

	public Course() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCredits() {
		return credits;
	}

	public void setCredits(Integer credits) {
		this.credits = credits;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", credits=" + credits + "]";
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
}