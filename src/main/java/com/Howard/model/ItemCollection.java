package com.Howard.model;

import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table
@Data
public class ItemCollection {
	private String name;
	private HashSet<CollectedItem> items;
	private ItemType allowedType;
}
