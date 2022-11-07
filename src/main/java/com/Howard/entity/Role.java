package com.Howard.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


/*
 * An entity class describing user roles for use with spring security
 * 
 * FIELDS:
 * name - the name of this role
 * users - the collection of users with this role.
 */


@Data
@NoArgsConstructor
@Entity
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private @NotEmpty String name;
	
	@ManyToMany(mappedBy="roles")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private @NotNull Collection<User> users;
	
	public Role(String name) 
	{
		this.name=name;
	}
}
