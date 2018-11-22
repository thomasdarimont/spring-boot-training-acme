package acme.todo.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import acme.todo.Todo;
import acme.todo.TodoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
class TodoController {

	private final TodoService todoService;

	/**
	 * <pre>
	 * {@code 
	 * curl http://localhost:8080/todos | jq -c .
	 * 
	 * http localhost:8080/todos
	 * }
	 * </pre>
	 * 
	 * @return
	 */
	@GetMapping
	public List<Todo> findAll() {
		return todoService.findAll();
	}

	
	/**
	 * <pre>{@code 
	 * curl http://localhost:8080/todos -H 'Content-type: application/json' -d '{"title":"Todo1"}'
	 * 
	 * echo '{"title":"Todo1"}' | http POST localhost:8080/todos
	 * }</pre>
	 * @param todo
	 * @return
	 */
	@PostMapping
	public Todo newTodo(@RequestBody Todo todo) {
		return todoService.create(todo);
	}
}
