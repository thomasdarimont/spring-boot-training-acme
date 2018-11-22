package acme.app.shop;

import lombok.Data;

@Data
public class Product {

	private String itemId;

	private String name;

	private String desc;

	private double price;
}