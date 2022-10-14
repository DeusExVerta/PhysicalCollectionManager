package com.Howard.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table
@Data
public class Location {
	@Id
	private int id;
	
	@ManyToOne
	private User user;
		
	@Column
	private String Name, Description;
}
