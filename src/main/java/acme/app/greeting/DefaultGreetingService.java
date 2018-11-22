package acme.app.greeting;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
class DefaultGreetingService implements GreetingService {

	private final GreetingProperties greetingProperties;

	@Override
	public String greet(String name) {
		return String.format("%s %s", greetingProperties.getSalutation(), name);
	}

	@PostConstruct
	public void init() {
		log.debug("Using salutation: {}", greetingProperties.getSalutation());
	}
}
