package com.Howard.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table
@Data
public class ItemType {
	private int id;
	private String name;
}
