package acme;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import acme.app.greeting.GreetingProperties;
import lombok.Data;

@Data
@ConfigurationProperties("acme")
public class AcmeProperties {

	private GreetingProperties greeting = new GreetingProperties();

	@Bean
	public GreetingProperties greetingProperties() {
		return greeting;
	}
}
