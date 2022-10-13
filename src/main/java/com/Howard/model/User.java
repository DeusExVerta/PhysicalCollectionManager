package com.Howard.model;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table
public class User {
	private String email;
	private String password;
	private Set<ItemCollection> itemCollections;
	private Set<Role> userRoles;
}
