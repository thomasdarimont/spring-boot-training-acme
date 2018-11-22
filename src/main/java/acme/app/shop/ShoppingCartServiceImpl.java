package acme.app.shop;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import acme.app.shop.catalog.CatalogService;
import acme.app.shop.pricing.PriceCalculationService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class ShoppingCartServiceImpl implements ShoppingCartService {

	private final CatalogService catalogService;

	private final PriceCalculationService priceCalculationService;

	private Map<String, ShoppingCart> cartDB = new HashMap<>();

	@Override
	public ShoppingCart getShoppingCart(String cartId) {
		ShoppingCart sc = cartDB.get(cartId);
		if (sc == null) {
			sc = new ShoppingCart();
			sc.setId(cartId);
			cartDB.put(cartId, sc);
		}
		return sc;
	}

	@Override
	public ShoppingCart calculateCartPrice(ShoppingCart sc) {
		priceCalculationService.priceShoppingCart(sc);
		cartDB.put(sc.getId(), sc);
		return sc;
	}

	@Override
	public ShoppingCart addToCart(String cartId, String itemId, int quantity) {
		ShoppingCart sc = getShoppingCart(cartId);
		if (quantity <= 0) {
			return sc;
		}
		Product product;
		product = getProduct(itemId);
		if (product == null) {
			return sc;
		}
		Optional<ShoppingCart.Item> cartItem = sc.getItems().stream()
				.filter(sci -> sci.getProduct().getItemId().equals(itemId)).findFirst();
		if (cartItem.isPresent()) {
			cartItem.get().setQuantity(cartItem.get().getQuantity() + quantity);
		} else {
			ShoppingCart.Item newCartItem = new ShoppingCart.Item();
			newCartItem.setProduct(product);
			newCartItem.setQuantity(quantity);
			newCartItem.setPrice(product.getPrice());
			sc.addItem(newCartItem);
		}
		calculateCartPrice(sc);
		cartDB.put(sc.getId(), sc);
		return sc;
	}

	@Override
	public ShoppingCart removeFromCart(String cartId, String itemId, int quantity) {
		ShoppingCart sc = getShoppingCart(cartId);
		if (quantity <= 0) {
			return sc;
		}
		Optional<ShoppingCart.Item> cartItem = sc.getItems().stream()
				.filter(sci -> sci.getProduct().getItemId().equals(itemId)).findFirst();
		if (cartItem.isPresent()) {
			if (cartItem.get().getQuantity() <= quantity) {
				sc.removeItem(cartItem.get());
			} else {
				cartItem.get().setQuantity(cartItem.get().getQuantity() - quantity);
			}
		}
		calculateCartPrice(sc);
		cartDB.put(sc.getId(), sc);
		return sc;
	}

	@Override
	public ShoppingCart checkoutShoppingCart(String cartId) {
		ShoppingCart sc = new ShoppingCart();
		sc.setId(cartId);
		cartDB.put(sc.getId(), sc);
		return sc;
	}

	private Product getProduct(String itemId) {
		return catalogService.getProduct(itemId);
	}
}