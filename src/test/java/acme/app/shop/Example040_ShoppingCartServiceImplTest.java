package acme.app.shop;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;

import acme.app.shop.Product;
import acme.app.shop.ShoppingCart;
import acme.app.shop.ShoppingCartService;
import acme.app.shop.catalog.CatalogService;
import acme.app.shop.pricing.PriceCalculationService;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class Example040_ShoppingCartServiceImplTest {

	@MockBean
	private PriceCalculationService priceCalculationService;

	@MockBean
	private CatalogService catalogService;

	@Autowired
	private ShoppingCartService shoppingCartService;

	@BeforeEach
	private void initMocks() {

		Product p1 = new Product();
		p1.setItemId("p1");
		p1.setPrice(100.0);
		when(catalogService.getProduct("p1")).thenReturn(p1);

		Product p2 = new Product();
		p2.setItemId("p2");
		p2.setPrice(100.0);

		when(catalogService.getProduct("p2")).thenReturn(p2);

		when(catalogService.getProduct("p3")).thenReturn(null);

		doAnswer(invocation -> {
			ShoppingCart sc = invocation.getArgument(0);
			sc.setCartItemTotal(100.0);
			sc.setCartTotal(120.0);
			sc.setShippingTotal(20.0);
			return null;
		}).when(priceCalculationService).priceShoppingCart(any(ShoppingCart.class));
	}

	@Test
	public void testGetNewShoppingCart() {

		ShoppingCart sc = shoppingCartService.getShoppingCart("123456");

		assertThat(sc).isNotNull();
		assertThat(sc.getId()).isEqualTo("123456");
		assertThat(sc.getCartItemTotal()).isEqualTo(0.0);
		assertThat(sc.getShippingTotal()).isEqualTo(0.0);
		assertThat(sc.getCartTotal()).isEqualTo(0.0);
		assertThat(sc.getItems()).hasSize(0);
	}

	@Test
	public void testAddNewItemToCart() {

		ShoppingCart sc = shoppingCartService.addToCart("123456", "p1", 1);

		assertThat(sc.getItems()).hasSize(1);
		assertThat(sc.getCartItemTotal()).isEqualTo(100.0);

		ShoppingCart.Item sci = sc.getItems().get(0);
		assertThat(sci.getProduct()).isNotNull();
		assertThat(sci.getProduct().getItemId()).isEqualTo("p1");
		assertThat(sci.getProduct().getPrice()).isEqualTo(100.0);
		assertThat(sci.getPrice()).isEqualTo(100.0);
		assertThat(sci.getQuantity()).isEqualTo(1);

		verify(priceCalculationService).priceShoppingCart(sc);
		verify(catalogService).getProduct("p1");

		// make sure the cart store is up to date
		sc = shoppingCartService.getShoppingCart("123456");
		assertThat(sc.getItems()).hasSize(1);
		assertThat(sc.getCartItemTotal()).isEqualTo(100.0);
	}
}
