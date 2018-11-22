package acme.app.shop;

public interface ShoppingCartService {

	ShoppingCart calculateCartPrice(ShoppingCart sc);

	ShoppingCart getShoppingCart(String cartId);

	ShoppingCart addToCart(String cartId, String itemId, int quantity);

	ShoppingCart removeFromCart(String cartId, String itemId, int quantity);

	ShoppingCart checkoutShoppingCart(String cartId);

}