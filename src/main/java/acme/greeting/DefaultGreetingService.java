package acme.greeting;

import org.springframework.stereotype.Service;

@Service
class DefaultGreetingService implements GreetingService {

	@Override
	public String greet(String name) {
		return String.format("Hello %s", name);
	}
}
