/**
 * @author admin
 * @date 06-10-2019
 */

package com.tuanpq.coursemng.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuanpq.coursemng.dao.RoleRepository;
import com.tuanpq.coursemng.entity.Role;
import com.tuanpq.coursemng.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public Role findByName(String name) {
		return roleRepository.findByName(name);
	}
	
}
