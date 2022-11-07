package com.Howard.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
/*
 * Entity class describing an Item Type
 * 
 * FIELDS:
 * name - the name of this item type
 * user - the set of users who have this item type
 */


@Entity
@Table
@Data
public class ItemType {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column
	private String name;
	
	@ManyToMany(mappedBy="types")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<User> user;
}
