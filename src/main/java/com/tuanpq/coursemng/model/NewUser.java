/**
 * @author admin
 * @date 06-10-2019
 */

package com.tuanpq.coursemng.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.tuanpq.coursemng.validation.FieldMatch;
import com.tuanpq.coursemng.validation.UniqueUsername;

@FieldMatch.List({@FieldMatch(first = "password", second = "rePassword", 
				message = "Password and repassword field must match!") })
public class NewUser {

	@UniqueUsername(message = "Username has already existed!")
	@NotBlank(message = "Username required!")
	private String username;

	@NotBlank(message = "First name required!")
	private String firstName;

	@NotBlank(message = "Last name required!")
	private String lastName;

	@NotBlank(message = "Email required!")
	private String email;

	@NotBlank(message = "Password required!")
	@Size(min = 6, message = "Password must be at least 6 characters.")
	private String password;

	private String rePassword;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}

	public NewUser(String username, String firstName, String lastName, String email,
			String password, String rePassword) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.rePassword = rePassword;
	}

	public NewUser() {
		super();
	}

	@Override
	public String toString() {
		return "NewUser [username=" + username + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", password="
				+ password + ", rePassword=" + rePassword + "]";
	}

}
