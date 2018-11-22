package acme.shop.pricing;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import acme.shop.ShoppingCart;

public class Example041_PriceCalculationServiceImplTest {

	PriceCalculationService priceCalculationService;

	@BeforeEach
	public void setup() {
		priceCalculationService = new PriceCalculationServiceImpl();
	}

	@Test
	void priceCartForEmptyShoppingCart() {

		ShoppingCart sc = new ShoppingCart();

		priceCalculationService.priceShoppingCart(sc);

		assertThat(sc.getShippingTotal()).isEqualTo(0.0);
		assertThat(sc.getCartItemTotal()).isEqualTo(0.0);
		assertThat(sc.getCartItemTotal()).isEqualTo(0.0);
	}

	@Test
	void priceCartForShoppingCart() {

		ShoppingCart sc = new ShoppingCart();
		ShoppingCart.Item sci1 = new ShoppingCart.Item();
		sci1.setPrice(10.0);
		sci1.setQuantity(1);
		sc.addItem(sci1);

		ShoppingCart.Item sci2 = new ShoppingCart.Item();
		sci2.setPrice(20.0);
		sci2.setQuantity(2);
		sc.addItem(sci2);

		priceCalculationService.priceShoppingCart(sc);

		assertThat(sc.getShippingTotal()).isEqualTo(6.99);
		assertThat(sc.getCartItemTotal()).isEqualTo(50.0);
		assertThat(sc.getCartTotal()).isEqualTo(56.99);
	}

	@Test
	void priceCartForShoppingCartWithFreeShipping() {

		ShoppingCart sc = new ShoppingCart();
		ShoppingCart.Item sci1 = new ShoppingCart.Item();
		sci1.setPrice(20.0);
		sci1.setQuantity(1);
		sc.addItem(sci1);

		ShoppingCart.Item sci2 = new ShoppingCart.Item();
		sci2.setPrice(40.0);
		sci2.setQuantity(2);
		sc.addItem(sci2);

		priceCalculationService.priceShoppingCart(sc);

		assertThat(sc.getShippingTotal()).isEqualTo(0.00);
		assertThat(sc.getCartItemTotal()).isEqualTo(100.0);
		assertThat(sc.getCartTotal()).isEqualTo(100.00);
	}

}