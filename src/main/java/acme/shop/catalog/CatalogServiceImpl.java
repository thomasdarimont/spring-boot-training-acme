package acme.shop.catalog;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import acme.shop.Product;

@Service
class CatalogServiceImpl implements CatalogService {

	@Value("${catalog.service.url:http://localhost:39999}")
	private String catalogServiceUrl;

	@Override
	public Product getProduct(String itemId) {

		try {

			RestTemplate restTemplate = new RestTemplate();
			// call remote catalog service
			ResponseEntity<Product> entity = restTemplate.getForEntity(catalogServiceUrl + "/product/" + itemId,
					Product.class);

			return entity.getBody();

		} catch (HttpClientErrorException e) {
			if (e.getRawStatusCode() == 404) {
				return null;
			} else {
				throw e;
			}
		}
	}
}