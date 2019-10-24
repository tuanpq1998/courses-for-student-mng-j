/**
 * @author admin
 * @date 03-10-2019
 */

package com.tuanpq.coursemng.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tuanpq.coursemng.dao.UserRepository;
import com.tuanpq.coursemng.entity.Role;
import com.tuanpq.coursemng.entity.User;
import com.tuanpq.coursemng.model.NewUser;
import com.tuanpq.coursemng.model.PasswordModel;
import com.tuanpq.coursemng.service.RoleService;
import com.tuanpq.coursemng.service.UserService;
import com.tuanpq.coursemng.utility.ConstantDelivery;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private ConstantDelivery constantDelivery;

	private org.springframework.security.core.userdetails.User getUserDetails(
			User user) {
		List<Role> roles = new ArrayList<>();
		roles.add(user.getRole());
		return new org.springframework.security.core.userdetails.User(
				user.getUsername(), user.getPassword(), roles);
	}

	private boolean isCorrectPassword(User user, String password) {
		return passwordEncoder.matches(password, user.getPassword());
	}
	
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(
					"Username " + username + " not found!");
		}
		return getUserDetails(user);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public void saveUser(User user) {
		userRepository.save(user);
	}

	@Override
	public void saveUser(NewUser newUser) {
		User user = new User();
		
		user.setId(0);
		user.setUsername(newUser.getUsername());
		user.setFirstName(newUser.getFirstName());
		user.setLastName(newUser.getLastName());
		user.setEmail(newUser.getEmail());
		user.setPassword(passwordEncoder.encode(newUser.getPassword()));
		
		user.setRole(roleService.findByName(constantDelivery.STUDENT_ROLE));
		
		saveUser(user);
	}

	@Override
	public boolean isUsernameExisted(String username) {
		return findByUsername(username) != null;
	}
	
	@Override
	public boolean changePassword(PasswordModel passwordModel, String username) {
		User user = findByUsername(username);
		if (user == null) return false;
		if (isCorrectPassword(user, passwordModel.getCurrentPassword())) {
			user.setPassword(passwordEncoder.encode(passwordModel.getNewPassword()));
			saveUser(user);
			return true;
		}
		return false;
	}

	@Override
	public void updateInfo(User user) {
		User userDB = findByUsername(user.getUsername());
		userDB.setFirstName(user.getFirstName());
		userDB.setLastName(user.getLastName());
		userDB.setEmail(user.getEmail());
		
		saveUser(userDB);
	}

}
