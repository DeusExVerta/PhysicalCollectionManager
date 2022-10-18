package com.Howard.service;


import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.Howard.entity.User;


public interface UserService extends UserDetailsService {
	User findByEmail(String email);

	void save(User user);
	
	Page<User> findAllUsers(Pageable pageable);
}
