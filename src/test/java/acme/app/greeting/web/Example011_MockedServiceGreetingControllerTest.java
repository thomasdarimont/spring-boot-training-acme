package acme.app.greeting.web;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import acme.app.greeting.GreetingService;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
class Example011_MockedServiceGreetingControllerTest {

	private MockMvc mockMvc;

	@MockBean
	private GreetingService greetingService;

	@BeforeEach
	void setup(@Autowired WebApplicationContext wac) {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

		when(greetingService.greet(anyString())) //
				.then(inv -> //
				"Hey " + inv.getArgument(0) //
				);
	}

	@Test
	void shouldGreetDefaultUserViaWeb() throws Exception {

		mockMvc.perform(get("/greet")) //
				.andExpect(status().isOk()) //
				.andExpect(content().string(containsString("Hey World"))) //
		;
	}

	@Test
	void shouldGreetCustomUserViaWeb() throws Exception {

		mockMvc.perform(get("/greet?name=Tom")) //
				.andExpect(status().isOk()) //
				.andExpect(content().string(containsString("Hey Tom"))) //
		;
	}
}
