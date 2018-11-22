package acme.mgmt.monitoring;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

@Component
class AcmeHealth implements HealthIndicator {

	@Override
	public Health health() {
		return Health.status(Status.UP) //
				.withDetail("component", "acme-demo") //
				.build();
	}

}
