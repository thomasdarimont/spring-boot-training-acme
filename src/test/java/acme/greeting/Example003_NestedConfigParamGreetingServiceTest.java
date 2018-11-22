package acme.greeting;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
class Example003_NestedConfigParamGreetingServiceTest {

	@Test
	void shouldGreetUser(@Autowired GreetingService greetingService) {
		assertThat(greetingService.greet("Tom")).isEqualTo("Huhu Tom");
	}

	@TestConfiguration
	static class CustomConfig {

		@Bean
		GreetingService greetingService() {
			return name -> "Huhu " + name;
		}
	}
}
