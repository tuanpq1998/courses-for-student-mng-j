/**
 * @author admin
 * @date 03-10-2019
 */

package com.tuanpq.coursemng.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.tuanpq.coursemng.entity.User;
import com.tuanpq.coursemng.model.NewUser;
import com.tuanpq.coursemng.model.PasswordModel;

public interface UserService extends UserDetailsService {

	public User findByUsername(String name);

	public void saveUser(User user);

	public void saveUser(NewUser newUser);

	public boolean isUsernameExisted(String username);

	public boolean changePassword(PasswordModel passwordModel, String username);

	public void updateInfo(User user);
}
