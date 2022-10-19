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
