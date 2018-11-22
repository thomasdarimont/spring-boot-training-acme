package acme.monitoring;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
class AcmeHealth implements HealthIndicator {

	@Override
	public Health health() {
		return Health.up().withDetail("component", "acme-demo").build();
	}

}
