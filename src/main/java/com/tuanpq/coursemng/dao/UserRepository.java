/**
 * @author admin
 * @date 03-10-2019
 */

package com.tuanpq.coursemng.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tuanpq.coursemng.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByUsername(String username);

}
