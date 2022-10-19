package com.Howard.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Howard.entity.Inventory;
import com.Howard.entity.InventoryItem;
import com.Howard.entity.ItemType;
import com.Howard.entity.Location;
import com.Howard.entity.User;
import com.Howard.service.InventoryService;
import com.Howard.service.ItemTypeService;
import com.Howard.service.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@AllArgsConstructor
public class InventoriesController {
	private UserService UsService;
	private InventoryService InService;
	private ItemTypeService ITService;
	
	@GetMapping("/Inventorys")
	public String showInventorys(Model model, Principal principal, @RequestParam("page") Optional<Integer> page) 
	{
		User user = UsService.findByEmail(principal.getName());
		if(user==null) 
		{
			String msg = "No user found with username "+principal.getName();
			log.error(msg);
			throw new UsernameNotFoundException(msg);
		}
		Page<Inventory> Inventorys = InService.findAll(user, PageRequest.of(page.orElse(1)-1,10));
		log.info("Getting Inventorys page "+(page.orElse(1)-1)+" of "+Inventorys.getTotalPages());
		model.addAttribute("Inventorys",Inventorys);
		model.addAttribute(
				"pages",
				IntStream.rangeClosed(1,Inventorys.getTotalPages())
					.boxed()
					.collect(Collectors.toList()));
		model.addAttribute("types",ITService.findByUser(user));
		return "Inventorys";
	}
	
	@PutMapping("/Inventorys")
	public String addInventory(Model model, Principal principal, @RequestParam("page")Optional<Integer> page, @RequestParam("name") String name, @RequestParam("itemType") String type) 
	{
		log.info(String.format("User %s adding Inventory %s with type %s",principal.getName(),name,type));
		Inventory inventory = new Inventory();
		User user = UsService.findByEmail(principal.getName());
		if(user==null) 
		{
			String msg = "No user found with username "+principal.getName();
			log.error(msg);
			throw new UsernameNotFoundException(msg);
		}
		if(ITService.existsByName(type) && user!=null && !name.isBlank()) 
		{
			inventory.setAllowedType(ITService.findByName(type));
			inventory.setName(name);
			inventory.setUser(user);
		}else 
		{
			return "redirect:/Inventorys?error";
		}
		
		InService.save(inventory);
		return "redirect:/Inventorys";
	}
	
	@DeleteMapping("/Inventorys/{name}")
	public String deleteInventory(Model model,Principal principal, @PathVariable(value = "name") String name) 
	{
		User user = UsService.findByEmail(principal.getName());
		if(user==null) 
		{
			String msg = "No user found with username "+principal.getName();
			log.error(msg);
			throw new UsernameNotFoundException(msg);
		}
		log.info(String.format("Found user %s, fetching Inventory",user.getEmail()));
		Inventory inventory = InService.findByName(user, name);
		if(inventory==null) 
		{
			log.error(String.format("No Inventory found for user %s with name %s",user.getEmail(),name));
			return "redirect:/Inventorys?error";
		}
		InService.delete(inventory);
		return "redirect:/Inventorys";
	}
	
	@GetMapping("/Inventorys/{name}")
	public String getSingleInventory(Model model, Principal principal, @PathVariable(value = "name") String name) 
	{
		User user = UsService.findByEmail(principal.getName());
		if(user==null) 
		{
			String msg = "No user found with username "+principal.getName();
			log.error(msg);
			throw new UsernameNotFoundException(msg);
		}
		log.info(String.format("Found user %s, fetching Inventory %s",user.getEmail(),name));
		Inventory inventory = InService.findByName(user, name);
		if(inventory==null) 
		{
			log.error(String.format("No Inventory found for user %s with name %s",user.getEmail(),name));
			return "redirect:/Inventorys?error";
		}
		log.info(String.format("Inventory found %s, %s", inventory, inventory.getItems()));
		model.addAttribute("Inventory",inventory);
		model.addAttribute("InventoryItem",new InventoryItem());
		model.addAttribute("locations", user.getLocations());
		model.addAttribute("location",new Location());
		return "SingleInventory";
	}
	
	@PutMapping("/type")
	public String addType(Model model, Principal principal, @RequestParam("name") String name) {
		User user = UsService.findByEmail(principal.getName());
		if(name.isBlank()) 
		{
			return "redirect:/Inventorys?error";
		}
		if(user==null) 
		{
			String msg = "No user found with username "+principal.getName();
			log.error(msg);
			throw new UsernameNotFoundException(msg);
		}
		ItemType type = ITService.findByName(name);
		if(type==null) 
		{
			type=new ItemType();
			type.setName(name);
			type.setUser(new HashSet<User>());
		}
		type.getUser().add(user);
		ITService.save(type);
		user.getTypes().add(type);
		UsService.save(user);
		return "redirect:/Inventorys";
	}
	
	@DeleteMapping("/type")
	public String deleteType(Model model,Principal principal, @RequestParam("itemType") String name) 
	{
		User user = UsService.findByEmail(principal.getName());
		ItemType type = ITService.findByName(name);
		if(user==null) 
		{
			String msg = "No user found with username "+principal.getName();
			log.error(msg);
			throw new UsernameNotFoundException(msg);
		}
		if(name.isBlank()||type==null||!user.getTypes().remove(type)) 
		{
			return "redirect:/Inventorys?error";
		}
		UsService.save(user);
		return "redirect:/Inventorys";
	}
	
}
