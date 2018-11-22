package acme.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http //
				.csrf().disable() // for demo purposes...
				.authorizeRequests() //
				.antMatchers("/app/**").hasRole("USER") //
				.antMatchers("/actuator/**").hasRole("ACTUATOR") //
				.antMatchers("/**").authenticated() //
				.and().httpBasic();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication() //
				.withUser("test").password("{noop}test").roles("USER", "ACTUATOR") //
		;
	}
}