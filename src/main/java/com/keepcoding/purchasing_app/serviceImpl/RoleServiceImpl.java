package com.keepcoding.purchasing_app.serviceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.keepcoding.purchasing_app.entity.Role;
import com.keepcoding.purchasing_app.repository.RoleRepository;
import com.keepcoding.purchasing_app.service.RoleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService{
	
	private final RoleRepository roleRepository;
	
	@Override
	public List<Role> listAllRoles() {
		
		return roleRepository.findAll();
	}

	@Override
	public Set<Role> findAllRoles() {
		
		return new HashSet<>(roleRepository.findAll());
	}

	@Override
	public Role findRoleById(Long id) {
		
		return roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found"));
	}

}
