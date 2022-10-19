package com.Howard;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.Howard.service.InventoryService;
import com.Howard.service.UserService;
import com.Howard.entity.Inventory;
import com.Howard.entity.InventoryItem;
import com.Howard.entity.User;
import com.Howard.service.InventoryItemService;

@SpringBootTest

public class ServiceTests {
	@Autowired
	InventoryItemService IIService;
	
	@Autowired
	InventoryService InService;
	
	@Autowired
	UserService UsService;
	
	public static String email = "name@site.com";
	
	@Test
	void testUserServiceSave() 
	{
		
		User u = new User();
		u.setEmail(email);
		u.setPassword("password");
		UsService.save(u);
		assertNotNull(UsService.findByEmail(email));
	}
	
	@Test
	void testUserServiceFindByEmail() 
	{
		User u = UsService.findByEmail(email);
		assertNotNull(u);
	}
	
	@Test
	void testInventoryServiceFindByName() 
	{
		User u = UsService.findByEmail(email);
		Inventory inv = new Inventory();
		inv.setName("newInventory");
		inv.setUser(u);
		Set<Inventory> invs = u.getItemCollections();
		invs.add(inv);
		u.setItemCollections(invs);
		InService.save(inv);
		Inventory inventory = InService.findByName(u, "newInventory");
		assertNotNull(inventory);
	}
	
	@Test
	void testInventoryItemServiceFindByInventoryAndName() 
	{
		User u = UsService.findByEmail(email);
		Inventory inventory = InService.findByName(u, "newInventory");
		InventoryItem item = new InventoryItem();
		item.setName("newItem");
		item.setInventory(inventory);
		inventory.getItems().add(item);
		InventoryItem actualItem =IIService.findByInventoryAndName(inventory,"newItem");
		assertNotNull(actualItem);
	}
	
}
