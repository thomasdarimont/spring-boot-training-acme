package acme.app.demo.stooges;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Stooges {

	private final Larry larry;

	private final Moe moe;

	private final Curly curly;

	public String getAlias() {
		return String.join(",",
				Arrays.asList((Stooge) curly, larry, moe).stream().map(Stooge::getName).collect(Collectors.toList()));
	}
}
