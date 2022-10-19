package com.Howard.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Howard.entity.Inventory;
import com.Howard.entity.User;
@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long>{
	public Page<Inventory> findByUser(User user,Pageable pageable);
	public Inventory findByUserAndName(User user, String name);
}
