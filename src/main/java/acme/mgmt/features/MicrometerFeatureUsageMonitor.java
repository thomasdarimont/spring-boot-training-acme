package acme.mgmt.features;

import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MicrometerFeatureUsageMonitor implements FeatureUsageMonitor {

	private final MeterRegistry registry;

	@Override
	public void record(Feature feature) {
		registry.counter("acme_app_feature_usage", "name", feature.name()).increment();
	}
}
