package com.Howard.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;
/*
 * An entity class describing a user of the application.
 * 
 * FIELDS:
 * email - the users email, this is the unique identifier used to identify this user throughout the aplication
 * password - the users password, this field is encrypted using BCrypt through our security configuration
 * itemColections - the set of inventory objects owned by this user
 * roles - the user roles held by this user, used by our security configuration to control page access.
 * types - the item types that this user has among their collections
 * locations - a set of all locations owned by this user
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table
public class User {
	
	@Id
	@NotEmpty(message = "Email should not be empty")
	@EqualsAndHashCode.Include
	@ToString.Include
	private String email;
	
	@Column(nullable = false, insertable=true, updatable=false)
	@NotEmpty(message = "Password should not be empty")
	@EqualsAndHashCode.Include
	@ToString.Include
	private @NonNull String password;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="user")
	private Set<Inventory> itemCollections;
	
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Set<Role> roles;
	
	@ManyToMany(cascade=CascadeType.ALL)
	private Set<ItemType> types;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="user")
	private Set<Location> locations;
}
