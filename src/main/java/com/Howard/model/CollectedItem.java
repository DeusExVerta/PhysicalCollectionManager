package com.Howard.model;

import java.time.ZonedDateTime;
import java.util.TreeMap;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table
@Data
public class CollectedItem {
		private ItemType type;
		private TreeMap<ZonedDateTime,Location> locationHistory;
		private TreeMap<ZonedDateTime,Float> priceHistory;
		private String priceSourceURL;
		private String imgURL;
		private int quality;
}
