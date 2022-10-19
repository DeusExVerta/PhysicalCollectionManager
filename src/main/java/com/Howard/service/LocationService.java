package com.Howard.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.Howard.entity.Location;
import com.Howard.entity.User;

public interface LocationService {
	public Location findByUserAndName(User user, String name);
	public Page<Location> findByUser(User user, Pageable pageable);
	public void save(Location location);
	public void delete(Location location);
}
