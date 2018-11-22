package acme.shop.pricing;

import org.springframework.stereotype.Service;

import acme.shop.ShoppingCart;

@Service
class PriceCalculationServiceImpl implements PriceCalculationService {

	@Override
	public void priceShoppingCart(ShoppingCart sc) {

		sc.setCartItemTotal(sc.getItems().stream().mapToDouble(this::calculateItemCosts).sum());
		sc.setShippingTotal(calculateShipping(sc));
		sc.setCartTotal(sc.getCartItemTotal() + sc.getShippingTotal());
	}

	private double calculateItemCosts(ShoppingCart.Item item) {
		return item.getPrice() * item.getQuantity();
	}

	private double calculateShipping(ShoppingCart sc) {

		if (sc.getCartItemTotal() <= 0.0) {
			return 0.0;
		}

		if (sc.getCartItemTotal() > 0.0 && sc.getCartItemTotal() < 25) {
			return 2.99;
		}

		if (sc.getCartItemTotal() >= 25.0 && sc.getCartItemTotal() < 49.99) {
			return 4.99;
		}

		if (sc.getCartItemTotal() >= 50.0 && sc.getCartItemTotal() < 74.99) {
			return 6.99;
		}

		return 0.0;
	}

}