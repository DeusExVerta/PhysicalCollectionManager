package com.Howard.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.Howard.entity.Location;
import com.Howard.entity.User;
import com.Howard.repository.LocationRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LocationServiceImplementation implements LocationService {
	LocationRepository LocRepo;
	@Override
	public Location findByUserAndName(User user,String name) {
		return LocRepo.findByUserAndName(user,name);
	}
	@Override
	public void save(Location location) {
		LocRepo.save(location);
	}
	@Override
	public void delete(Location location) {
		LocRepo.delete(location);
	}
	@Override
	public Page<Location> findByUser(User user,Pageable pageable) {
		return LocRepo.findByUser(user,pageable);
	}

}
