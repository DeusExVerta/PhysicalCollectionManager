package com.Howard.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
/*
 * Entity class describing a location for inventory items
 * 
 * FIELDS:
 * user - the user to whom this location belongs
 * name - the name of this location
 * description - the full description of this location
 */
@Entity
@Table
@Data
@NoArgsConstructor
public class Location {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private User user;
		
	@Column
	private String name;
	
	@Column
	private String description;	
}
