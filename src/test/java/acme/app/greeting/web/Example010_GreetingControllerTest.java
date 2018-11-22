package acme.app.greeting.web;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class Example010_GreetingControllerTest {

	private MockMvc mockMvc;

	@BeforeEach
	void setup(@Autowired WebApplicationContext wac) {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	void shouldGreetDefaultUserViaWeb() throws Exception {

		mockMvc.perform(get("/greet")) //
				.andExpect(status().isOk()) //
				.andExpect(content().string(containsString("Hello World"))) //
		;
	}
	
	@Test
	void shouldGreetCustomUserViaWeb() throws Exception {

		mockMvc.perform(get("/greet?name=Tom")) //
				.andExpect(status().isOk()) //
				.andExpect(content().string(containsString("Hello Tom"))) //
		;
	}
}
