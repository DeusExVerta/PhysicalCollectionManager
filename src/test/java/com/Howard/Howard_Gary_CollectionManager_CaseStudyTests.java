package com.Howard;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import com.Howard.entity.ItemType;
import com.Howard.entity.User;
import com.Howard.repository.ItemTypeRepository;
import com.Howard.repository.LocationRepository;
import com.Howard.repository.RoleRepository;
import com.Howard.repository.UserRepository;

@SpringBootTest

class Howard_Gary_CollectionManager_CaseStudyTests {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	RoleRepository roleRepo;
	
	@Autowired
	LocationRepository locationRepo;
	
	@Autowired
	ItemTypeRepository ItRepo;
	
	@Test
	  void contextLoads() {
	  }
	
	@Test
	void testUserRepositoryFindByEmail() 
	{
		User user = new User();
		user.setEmail("name@site.com");
		user.setPassword("password");
		userRepo.save(user);
		
		User actual = userRepo.findByEmail(user.getEmail());
		
		assertEquals(user,actual);
		assertNotEquals("bademail",actual.getEmail());
		userRepo.delete(actual);
	}
	
	@Test
	void testUserRepositoryFindAll() 
	{
		List<User> users = new ArrayList<User>(100);
		for(int i=0;i<100;i++) 
		{
			User user=new User();
			user.setEmail("user"+i+"@site.com");
			user.setPassword("password");
			users.add(user);
		}
		userRepo.saveAll(users);
		
		List<User> actual = userRepo.findAll(Pageable.unpaged()).toList();
		assertTrue(actual.containsAll(users));
		userRepo.deleteAll(users);
	}
	
	
	@ParameterizedTest
	@ValueSource(strings = {"ROLE_USER","ROLE_ADMIN"})
	void testRoleRepositoryFindByName(String role) 
	{
		assertNotNull(roleRepo.findByName(role));
	}
	
	@Test
	void testItemTypeRepositoryFindByName() 
	{
		ItemType type= new ItemType();
		type.setName("Junk");
		User u = userRepo.findByEmail("Dude@dude.bro");
		type.setUser(new HashSet<User>());
		type.getUser().add(u);
		ItRepo.save(type);
		assertNotNull(ItRepo.findByName("Junk"));
		ItRepo.delete(type);
		assertNull(ItRepo.findByName("Junk"));
	}
	@Test
	void testItemTypeRepositoryExistsByName() {
		ItemType type= new ItemType();
		type.setName("Junk");
		User u = userRepo.findByEmail("Dude@dude.bro");
		type.setUser(new HashSet<User>());
		type.getUser().add(u);
		ItRepo.save(type);
		assertTrue(ItRepo.existsByName("Junk"));
		ItRepo.delete(type);
		assertFalse(ItRepo.existsByName("Junk"));
	}

}
