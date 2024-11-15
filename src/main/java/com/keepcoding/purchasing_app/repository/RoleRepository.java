package com.keepcoding.purchasing_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.keepcoding.purchasing_app.entity.Role;


public interface RoleRepository extends JpaRepository<Role, Long>{
	
	Role findByName(String name);

}
