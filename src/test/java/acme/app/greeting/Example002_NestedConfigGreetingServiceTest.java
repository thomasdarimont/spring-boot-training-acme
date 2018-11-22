package acme.app.greeting;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import acme.app.greeting.GreetingService;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
class Example002_NestedConfigGreetingServiceTest {

	@Autowired
	GreetingService greetingService;

	@Test
	void shouldGreetUser() {
		assertThat(greetingService.greet("Tom")).isEqualTo("Hi Tom");
	}

	@TestConfiguration
	static class CustomConfig {

		@Bean
		GreetingService greetingService() {
			return name -> "Hi " + name;
		}
	}
}
