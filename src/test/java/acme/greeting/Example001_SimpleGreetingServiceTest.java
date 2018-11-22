package acme.greeting;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
class Example001_SimpleGreetingServiceTest {

	@Autowired
	GreetingService greetingService;

	@Test
	void shouldGreetUser() {
		assertThat(greetingService.greet("Tom")).isEqualTo("Hello Tom");
	}
}
