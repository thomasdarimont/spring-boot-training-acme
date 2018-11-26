package acme.app.todo;

import java.util.List;

public interface TodoService {

	Todo create(NewTodo todo);

	List<Todo> findAll();
	
	List<Todo> findByTitle(String titlePattern);
}
