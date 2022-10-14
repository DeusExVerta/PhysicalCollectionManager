package com.Howard.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table
@Data
public class ItemType {
	@Id
	private int id;
	@Column
	private String name;
}
