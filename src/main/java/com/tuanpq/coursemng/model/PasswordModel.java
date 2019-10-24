/**
 * @author admin
 * @date 06-10-2019
 */

package com.tuanpq.coursemng.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.tuanpq.coursemng.validation.FieldMatch;

@FieldMatch.List({@FieldMatch(first = "newPassword", second = "reNewPassword", 
				message = "Password and repassword field must match!") })
public class PasswordModel {

	@NotBlank(message = "Old password required!")
	private String currentPassword;
	
	@NotBlank(message = "New password required!")
	@Size(min = 6, message = "New password must be at least 6 characters.")
	private String newPassword;
	
	private String reNewPassword;

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getReNewPassword() {
		return reNewPassword;
	}

	public void setReNewPassword(String reNewPassword) {
		this.reNewPassword = reNewPassword;
	}

	public PasswordModel() {
		super();
	}

}
