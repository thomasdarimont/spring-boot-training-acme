package acme.app.shop.pricing;

import acme.app.shop.ShoppingCart;

public interface PriceCalculationService {

	void priceShoppingCart(ShoppingCart sc);

}