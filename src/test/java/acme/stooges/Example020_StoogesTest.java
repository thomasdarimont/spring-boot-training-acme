package acme.stooges;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
class Example020_StoogesTest {

	@Nested
	static class NormalStooges extends Example020_StoogesTest {

		@Test
		public void testStooges(@Autowired Stooges stooges) {
			assertThat(stooges.getAlias()).contains("Curly,Larry,Moe");
		}
	}

	@Nested
	static class WithMockedStooges extends Example020_StoogesTest {

		@Test
		public void testOneMockedStooges(@Autowired Stooges stooges) {
			assertThat(stooges.getAlias()).contains("Curly,LarryMocked,Moe");
		}

		@TestConfiguration
		static class Config {

			// Override only larry
			@Bean
			Larry larry() {
				return new Larry() {
					@Override
					public String getName() {
						return "LarryMocked";
					}
				};
			}
		}
	}

	@Nested
	static class WithClassMockedStooges extends Example020_StoogesTest {

		@MockBean
		Moe moe;

		@BeforeEach
		public void before() {
			when(moe.getName()).thenReturn("MoeMocked");
		}

		@Test
		public void testOneMockedStooges(@Autowired Stooges stooges) {
			assertThat(stooges.getAlias()).contains("Curly,Larry,MoeMocked");
		}
	}
}
