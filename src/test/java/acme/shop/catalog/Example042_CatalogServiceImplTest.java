package acme.shop.catalog;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.util.StreamUtils;

import com.github.jenspiegsa.wiremockextension.ConfigureWireMock;
import com.github.jenspiegsa.wiremockextension.InjectServer;
import com.github.jenspiegsa.wiremockextension.WireMockExtension;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import com.github.tomakehurst.wiremock.core.Options;

import acme.shop.Product;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@ExtendWith(WireMockExtension.class)
public class Example042_CatalogServiceImplTest {

	@ConfigureWireMock
	Options options = wireMockConfig() //
			.dynamicPort().port(39999) //
			.notifier(new ConsoleNotifier(true));

	@InjectServer
	WireMockServer serverMock;

	@Test
	public void getProduct(@Autowired CatalogService catalogService) throws Exception {

		// stub the WireMock server to return a response
		ResponseDefinitionBuilder fakeResponse = aResponse() //
				.withStatus(200) //
				.withHeader("Content-type", "application/json") //
				.withBody(loadFakeResponse("catalog-response.json")) //
		;

		stubFor(get(urlEqualTo("/product/111111")) //
				.willReturn(fakeResponse));

		Product product = catalogService.getProduct("111111");

		assertThat(product).isNotNull();
		assertThat(product.getItemId()).isNotNull();
		assertThat(product.getItemId()).isEqualTo("111111");
		assertThat(product.getPrice()).isEqualTo(new Double("100.0"));

		verify(getRequestedFor(urlEqualTo("/product/111111")));
	}

	@Test
	public void getProductWhenCatalogServerRespondsWithNotFound(@Autowired CatalogService catalogService) {

		stubFor(get(urlEqualTo("/product/111111")) //
				.willReturn(aResponse().withStatus(404)));

		Product product = catalogService.getProduct("111111");

		assertThat(product).isNull();

		verify(getRequestedFor(urlEqualTo("/product/111111")));
	}

	private String loadFakeResponse(String responseFile) {
		try {
			InputStream resource = getClass().getClassLoader().getResourceAsStream(responseFile);
			return StreamUtils.copyToString(resource, StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}