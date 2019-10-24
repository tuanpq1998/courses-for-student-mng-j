/**
 * @author admin
 * @date 03-10-2019
 */

package com.tuanpq.coursemng.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tuanpq.coursemng.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	public Role findByName(String name);
}
