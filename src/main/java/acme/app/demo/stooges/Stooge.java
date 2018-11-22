package acme.app.demo.stooges;

public interface Stooge {

	default String getName() {
		return getClass().getSimpleName();
	}
}
