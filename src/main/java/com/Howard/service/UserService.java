package com.Howard.service;


import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.Howard.entity.User;


public interface UserService extends UserDetailsService {
	User findByEmail(String email);

	void save(User user);
	
	Slice<User> findAllUsers(Pageable pageable);
}
