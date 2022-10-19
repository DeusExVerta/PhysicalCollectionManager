package com.Howard.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table
@Data
public class Inventory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private User user;
	
	@Column(nullable = false, unique = true)
	@NotBlank
	private String name;
	
	@ManyToOne
	@NotNull
	private ItemType allowedType;
	
	@OneToMany(cascade=CascadeType.ALL)
	@NotNull
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private List<InventoryItem> items;
	
}
