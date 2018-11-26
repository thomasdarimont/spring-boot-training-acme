package acme.app.todo.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import acme.app.todo.NewTodo;
import acme.app.todo.Todo;
import acme.app.todo.TodoService;
import acme.mgmt.features.Feature;
import acme.mgmt.features.FeatureUsageMonitor;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
class TodoController {

	private final TodoService todoService;

	private final Optional<FeatureUsageMonitor> featureUsageMonitor;

	/**
	 * <pre>
	 * {@code 
	 * curl -u test:test http://localhost:8080/todos | jq -c .
	 * 
	 * http --auth test:test localhost:8080/todos
	 * }
	 * </pre>
	 * 
	 * @return
	 */
	@GetMapping
	public List<Todo> findAll() {
		featureUsageMonitor.ifPresent(Feature.ACME_TODO_FIND_ALL::record);
		return todoService.findAll();
	}
	
	/**
	 * <pre>
	 * {@code 
	 * curl -u test:test http://localhost:8080/todos/search/by-title\?pattern=To | jq -c .
	 * 
	 * http --auth test:test localhost:8080/todos/search/by-title\?pattern=To
	 * }
	 * </pre>
	 * 
	 * @return
	 */
	@GetMapping("/search/by-title")
	public List<Todo> findByTitle(@RequestParam(defaultValue="%") String pattern) {
		featureUsageMonitor.ifPresent(Feature.ACME_TODO_FIND_BY_TITLE::record);
		return todoService.findByTitle("%" + pattern + "%");
	}


	/**
	 * <pre>
	 * {@code 
	 * curl -u test:test http://localhost:8080/todos -H 'Content-type: application/json' -d '{"title":"Todo1"}'
	 * 
	 * echo '{"title":"Todo1"}' | http POST localhost:8080/todos --auth test:test
	 * }
	 * </pre>
	 * 
	 * @param todo
	 * @return
	 */
	@PostMapping
	public Todo newTodo(@Valid @RequestBody NewTodo todo) {
		featureUsageMonitor.ifPresent(Feature.ACME_TODO_NEW::record);
		return todoService.create(todo);
	}
}
