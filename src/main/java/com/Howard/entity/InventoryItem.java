package com.Howard.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import lombok.Data;
import lombok.EqualsAndHashCode;


/*
 * Entity class describing a single Inventory Item
 * 
 * FIELDS:
 * inventory - the inventory object this item belongs too.
 * name - the name of this item
 * itemType - the type of this item
 * quantity - the total number of this item
 * quality - the quality of this item
 * location - the location of this item
 * price - the price of this item
 */

@Entity
@Table
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
public class InventoryItem {
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long id;
		
		@ManyToOne
		private Inventory inventory;
		
		@Column
		@EqualsAndHashCode.Include
		private String name;
		
		@ManyToOne
		private ItemType type;
		
		@Column
		private int quantity;
		
		@Column
		private Integer quality;
		
		@ManyToOne
		@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
		private Location location;
		
		@Column
		@Audited
		private Float price;
		
		@Version Long version;
		
		public void addQuantity(int quantity) 
		{
			this.quantity+=quantity;
		}
}
