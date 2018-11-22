package acme.mgmt.features;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "acme-features")
public class FeaturesEndpoint {

	private Map<Feature, Boolean> features = new ConcurrentHashMap<>(defaultFeatures());

	@ReadOperation
	public Map<Feature, Boolean> features() {
		return features;
	}

	private static Map<Feature, Boolean> defaultFeatures() {

		Map<Feature, Boolean> map = new HashMap<>();

		map.put(Feature.ACME_FEATURE_1, true);
		map.put(Feature.ACME_FEATURE_2, true);

		return map;
	}

	@ReadOperation
	public Boolean feature(@Selector Feature feature) {
		return features.get(feature);
	}

	@WriteOperation
	public void configureFeature(@Selector Feature feature, Boolean toggle) {
		features.put(feature, toggle);
	}

	@DeleteOperation
	public void deleteFeature(@Selector Feature feature) {
		features.remove(feature);
	}

}