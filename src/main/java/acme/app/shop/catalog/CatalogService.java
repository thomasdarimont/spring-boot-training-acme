package acme.app.shop.catalog;

import acme.app.shop.Product;

public interface CatalogService {

	Product getProduct(String itemId);

}