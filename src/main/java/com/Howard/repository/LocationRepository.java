package com.Howard.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Howard.entity.Location;
import com.Howard.entity.User;

@Repository
public interface LocationRepository extends JpaRepository<Location,Long> {
	public Location findByUserAndName(User user, String name);
	public Page<Location> findByUser(User user,Pageable pageable);
}
