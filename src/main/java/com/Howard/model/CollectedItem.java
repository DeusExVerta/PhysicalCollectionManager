package com.Howard.model;

import java.time.ZonedDateTime;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyTemporal;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table
@Data
public class CollectedItem {
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long id;
		
		@Column
		private ItemType type;
		@Column
		private String priceSourceURL;
		@Column
		private String imgURL;
		@Column
		private int quality;
		
		//TODO: Determine appropriate annotation for auto-generated timestamps
		@OneToMany(cascade = CascadeType.ALL)
		private Map<ZonedDateTime,Location> locationHistory;
		
		//TODO: Determine appropriate annotation for auto-generated timestamps for this collection of primitives
		@ElementCollection
		@CollectionTable(name = "price_history", joinColumns= @JoinColumn(name = "price"))
		@MapKeyTemporal(TemporalType.TIMESTAMP)
		private Map<ZonedDateTime,Float> priceHistory;
		
}
