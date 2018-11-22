package acme.todo;

import java.util.List;

public interface TodoService {

	Todo create(Todo todo);

	List<Todo> findAll();
}
