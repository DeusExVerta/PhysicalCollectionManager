package com.Howard.service;

import java.util.List;

import com.Howard.entity.ItemType;
import com.Howard.entity.User;

public interface ItemTypeService {
	public List<ItemType> findByUser(User user);
	public Boolean existsByName(String name);
	public ItemType findByName(String name);
	public void save(ItemType type);
}
