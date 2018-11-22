package acme.todo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Todo {

	private Long id;

	private String title;

	public Todo(String title) {
		this.title = title;
	}
}
