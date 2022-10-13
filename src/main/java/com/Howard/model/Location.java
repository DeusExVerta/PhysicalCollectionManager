package com.Howard.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table
@Data
public class Location {
	private User user;
	private int id;
	private String Name, Description;
}
