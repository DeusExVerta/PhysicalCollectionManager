package com.Howard.service;

import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Howard.entity.Role;
import com.Howard.entity.User;
import com.Howard.repository.RoleRepository;
import com.Howard.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImplementation implements UserService {

	private UserRepository userRepository;
	private RoleRepository roleRepo;
    private PasswordEncoder passwordEncoder;

	@Override
	public User findByEmail(String email) {
		log.info("finding user with email " + email);
		return userRepository.findByEmail(email);
	}

	@Override
	public void save(User user) {
		Set<Role> roles = user.getRoles();
		if(roles==null) 
		{
			roles = new HashSet<Role>();
		}
		if(roles.isEmpty()) {
			Role role = roleRepo.findByName("ROLE_USER");
			if(role==null) 
			{
				role=new Role("ROLE_USER");
			}
			roles.add(role);	
		}
		user.setRoles(roles);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		log.info("Saving user "+ user);
		userRepository.save(user);
	}

	@Override
	public Page<User> findAllUsers(Pageable pageable) {
	     return userRepository.findAll(pageable);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = findByEmail(username);
        if(user != null){
        	log.info(String.format("found user %s with password %s",user.getEmail(),user.getPassword()));
            return new org.springframework.security.core.userdetails.User(user.getEmail()
                    , user.getPassword(),
                    user.getRoles().stream()
                            .map((role) -> new SimpleGrantedAuthority(role.getName()))
                            .collect(Collectors.toList()));
        }else {
        	log.error("Username not found in database");
            throw new UsernameNotFoundException("Invalid email or password");
        }
	}
	
//	public void update(User user) 
//	{
//		userRepository.update(user);
//	}

}
