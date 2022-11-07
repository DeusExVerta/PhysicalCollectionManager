package com.Howard.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.Howard.entity.Inventory;
import com.Howard.entity.InventoryItem;
import com.Howard.entity.Location;
import com.Howard.entity.User;
import com.Howard.exceptions.InventoryNotFoundException;
import com.Howard.exceptions.LocationNotFoundException;
import com.Howard.service.InventoryItemService;
import com.Howard.service.InventoryService;
import com.Howard.service.LocationService;
import com.Howard.service.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
/*
 * Controller class focused on Single inventory operations.
 */
@Slf4j
@Controller
@AllArgsConstructor
public class SingleInventoryController {
	
	private UserService UsService;
	
	private LocationService LocService;
	
	private InventoryService InService;
	
	private InventoryItemService IiService;

	
	/*
	 * handler method for handling adding locations for a user.
	 */
	@PutMapping("/Location")
	public String addLocation(Principal principal, @ModelAttribute("location")Location location , @RequestHeader(value = HttpHeaders.REFERER, required = false) final String referrer) 
	{
		User user = UsService.findByEmail(principal.getName());
		if(user==null)
		{
			String msg = "No user found with username "+principal.getName();
			log.error(msg);
			throw new UsernameNotFoundException(msg);
		}
		location.setUser(user);
		log.info("Saving location "+location.getName()+","+shortString(location.getDescription())+"...");
		LocService.save(location);
		if(referrer != null) 
		{
			String[] strings = referrer.split("/");
			StringBuilder sb = new StringBuilder();
			sb.append("redirect:");
			for(int i=1;i<strings.length;i++) 
			{
				sb.append("/");
				sb.append(strings[i]);
			}
			return sb.toString();
		}
		return "redirect:/Inventorys";
	}
	
	
	/*
	 * handler method for handling deleting locations for a user.
	 */
	@DeleteMapping("/Location/{name}")
	public String deleteLocation(Principal principal, @PathVariable("name") String name, @RequestHeader(value = HttpHeaders.REFERER, required = false) final String referrer) 
	{
		User user = UsService.findByEmail(principal.getName());
		if(user==null)
		{
			String msg = "No user found with username "+principal.getName();
			log.error(msg);
			throw new UsernameNotFoundException(msg);
		}
		Location loc1 = LocService.findByUserAndName(user, name);
		if(loc1==null) 
		{
			throw new LocationNotFoundException("No location found with name "+name+" for user "+user.getEmail());
		}
		log.info("Deleting location "+loc1.getName()+","+shortString(loc1.getDescription())+"...");
		LocService.delete(loc1);
		if(referrer != null) 
		{
			String[] strings = referrer.split("/");
			StringBuilder sb = new StringBuilder();
			sb.append("redirect:");
			for(int i=1;i<strings.length;i++) 
			{
				sb.append("/");
				sb.append(strings[i]);
			}
			return sb.toString();
		}
		return "redirect:/Inventorys";
	}
	
	/*
	 * handler method for handling adding items to a collection.
	 */
	@PutMapping("/Inventorys/{name}/item")
	public String addItem(Principal principal, @Valid @ModelAttribute("InventoryItem") InventoryItem item, @PathVariable("name")String inventoryName, @RequestParam("locationName") String locationName) 
	{
		User user = UsService.findByEmail(principal.getName());
		if(user==null)
		{
			String msg = "No user found with username "+principal.getName();
			log.error(msg);
			throw new UsernameNotFoundException(msg);
		}
		Inventory inventory= InService.findByName(user, inventoryName);
		if(inventory==null)
		{
			throw new InventoryNotFoundException(String.format("No inventory found with name %s for user %s",inventoryName,user.getEmail()));
		}
		List<InventoryItem> itemList = inventory.getItems();
		Location location = LocService.findByUserAndName(user, locationName);
		if(location == null)
		{
			throw new LocationNotFoundException(String.format("No inventory found with name %s for user %s",inventoryName,user.getEmail()));
		}
		item.setLocation(location);
		item.setInventory(inventory);
		item.setType(inventory.getAllowedType());
		item.setQuantity(1);
		if(itemList.contains(item)) 
		{
			log.info(String.format("Item %s exists in Inventory %s", item.getName(),inventory.getName()));
			itemList.get(itemList.indexOf(item)).addQuantity(item.getQuantity());
		}else{
			itemList.add(item);
		}
		InService.save(inventory);
		return String.format("redirect:/Inventorys/%s",inventoryName);
	}
	
	@DeleteMapping("/Inventorys/{name}/item")
	public String deleteItem(Principal principal, @Valid @ModelAttribute("InventoryItem") InventoryItem item, @PathVariable("name")String inventoryName) 
	{
		User user = UsService.findByEmail(principal.getName());
		if(user==null)
		{
			String msg = "No user found with username "+principal.getName();
			log.error(msg);
			throw new UsernameNotFoundException(msg);
		}
		Inventory inventory= InService.findByName(user, inventoryName);
		if(inventory==null) 
		{
			throw new InventoryNotFoundException(String.format("No inventory found with name %s for user %s",inventoryName,user.getEmail()));
		}
		List<InventoryItem> itemList = inventory.getItems();
		if(itemList.contains(item)) 
		{
			log.info(String.format("Item %s exists in Inventory %s", item.getName(),inventory.getName()));
			
		}
		InService.save(inventory);
		return String.format("redirect:/Inventorys/%s",inventoryName);
	}
	@GetMapping("/Inventorys/{inventoryName}/{itemName}")
	public String viewItem(Model model, Principal principal, @PathVariable("inventoryName")String inventoryName, @PathVariable("itemName") String itemName) 
	{
		User user = UsService.findByEmail(principal.getName());
		if(user==null)
		{
			String msg = "No user found with username "+principal.getName();
			log.error(msg);
			throw new UsernameNotFoundException(msg);
		}
		Inventory inventory = InService.findByName(user, inventoryName);
		if(inventory==null) 
		{
			throw new InventoryNotFoundException(String.format("No inventory found with name %s for user %s",inventoryName,user.getEmail()));
		}
		InventoryItem item = IiService.findByInventoryAndName(inventory, itemName);
		if(!inventory.getItems().contains(item)) 
		{
			return "redirect:/Inventorys/"+inventoryName+"?error";
		}
		model.addAttribute("Item", item);
		return "SingleItem";
	}
	
	@GetMapping("/Locations")
	public String getLocations(Model model, Principal principal, @RequestParam("page") Optional<Integer> page) 
	{	
		User user = UsService.findByEmail(principal.getName());
		if(user==null)
		{
			String msg = "No user found with username "+principal.getName();
			log.error(msg);
			throw new UsernameNotFoundException(msg);
		}
		Page<Location> locations = LocService.findByUser(user, PageRequest.of(page.orElse(1)-1, 10));
		log.info(String.format("Found %s locations for user %s, %s",locations.getNumberOfElements(),user,locations.isEmpty()));
		model.addAttribute(
				"pages",
				IntStream.rangeClosed(1,locations.getTotalPages())
					.boxed()
					.collect(Collectors.toList()));
		model.addAttribute("locations", locations);
		model.addAttribute("location",new Location());
		return "locations";
	}
	
	
		
	private String shortString(String string) 
	{
		return string.substring(0,string.length()>10?10:string.length());
	}
}
