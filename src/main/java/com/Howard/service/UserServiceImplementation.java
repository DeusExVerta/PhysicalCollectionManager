package com.Howard.service;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.Howard.dto.UserDTO;
import com.Howard.model.User;
import com.Howard.model.Role;
import com.Howard.repository.UserRepository;

public class UserServiceImplementation implements UserService {

	private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void save(UserDTO registration) {
		User user = new User();
		user.setEmail(registration.getEmail());
		Set<Role> roles = registration.getRoles();
		if(roles==null) 
		{
			roles = new HashSet<Role>();
		}
		if(roles.isEmpty()) {
			Role role = new Role();
			role.setName("ROLE_USER");
			roles.add(role);	
		}
		user.setUserRoles(roles);
		user.setPassword(passwordEncoder.encode(registration.getPassword()));
		userRepository.save(user);
	}

	@Override
	public List<UserDTO> findAllUsers() {
	     return userRepository.findAll().stream()
	                .map((user) -> mapToUserDto(user))
	                .collect(Collectors.toList());	
	}
	
	private UserDTO mapToUserDto(User user){
        UserDTO userDto = new UserDTO();
        userDto.setEmail(user.getEmail());
        return userDto;
    }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
        if(user != null){
            return new org.springframework.security.core.userdetails.User(user.getEmail()
                    , user.getPassword(),
                    user.getUserRoles().stream()
                            .map((role) -> new SimpleGrantedAuthority(role.getName()))
                            .collect(Collectors.toList()));
        }else {
            throw new UsernameNotFoundException("Invalid email or password");
        }
	}

}
