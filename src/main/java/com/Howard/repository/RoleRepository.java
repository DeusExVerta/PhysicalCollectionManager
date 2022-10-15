package com.Howard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Howard.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByName(String name);
}
