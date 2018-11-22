package acme.stooges;

public interface Stooge {

	default String getName() {
		return getClass().getSimpleName();
	}
}
