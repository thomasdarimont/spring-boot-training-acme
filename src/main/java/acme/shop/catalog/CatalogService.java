package acme.shop.catalog;

import acme.shop.Product;

public interface CatalogService {

	Product getProduct(String itemId);

}