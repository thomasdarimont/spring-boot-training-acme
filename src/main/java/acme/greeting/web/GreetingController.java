package acme.greeting.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import acme.greeting.GreetingService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
class GreetingController {

	private final GreetingService greetingService;

	@GetMapping("/greet")
	Object greet(@RequestParam(defaultValue = "World") String name) {
		return greetingService.greet(name);
	}
}
