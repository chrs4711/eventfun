package de.chris.fun.eventfun.dtos;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a cart. Used when serializing from/to json
 * 
 * @author chris
 *
 */
public class Cart {

	private String created;

	private String modified;

	private List<Item> items = new ArrayList<>();

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getModified() {
		return modified;
	}

	public void setModified(String modified) {
		this.modified = modified;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	public void addItem(Item item) {
		items.add(item);
	}

}
