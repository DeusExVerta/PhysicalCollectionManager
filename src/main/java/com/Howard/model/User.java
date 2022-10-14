package com.Howard.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table
public class User {
	
	@Id
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="user")
	private Set<ItemCollection> itemCollections;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="user")
	private Set<Role> userRoles;
}
