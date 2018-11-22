package acme.shop;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ShoppingCart {

	private String id;

	private double cartItemTotal;

	private double shippingTotal;

	private double cartTotal;

	private List<Item> items = new ArrayList<Item>();

	public void resetItems() {
		items = new ArrayList<Item>();
	}

	public void addItem(Item it) {
		if (it == null) {
			return;
		}
		items.add(it);
	}

	public boolean removeItem(Item it) {
		if (it == null) {
			return false;
		}
		return items.remove(it);
	}

	@Data
	public static class Item {

		private double price;

		private int quantity;

		private Product product;
	}
}
