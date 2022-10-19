package com.Howard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Howard.entity.ItemType;
import com.Howard.entity.User;

@Repository
public interface ItemTypeRepository extends JpaRepository<ItemType,Integer>{
	public List<ItemType> findByUser(User user);
	public boolean existsByName(String name);
	public ItemType findByName(String name);
}
