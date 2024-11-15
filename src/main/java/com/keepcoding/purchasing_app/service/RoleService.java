package com.keepcoding.purchasing_app.service;

import java.util.List;
import java.util.Set;

import com.keepcoding.purchasing_app.entity.Role;

public interface RoleService {
	
	List<Role> listAllRoles();
	
	Set<Role> findAllRoles();
	
	Role findRoleById(Long id);

}
