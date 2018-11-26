package acme.app.todo;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
class DefaultTodoService implements TodoService {

	private final TodoRepository todoRepository;

	private final TodoMapper todoMapper;

	@Transactional
	@Override
	public Todo create(NewTodo todo) {
		return todoMapper.toTodo(todoRepository.save(todoMapper.toTodoEntity(todo)));
	}

	@Override
	public List<Todo> findAll() {
		return toTodoList(todoRepository.findAll());
	}

	@Override
	public List<Todo> findByTitle(String titlePattern) {
		return toTodoList(todoRepository.findByTitleLike(titlePattern));
	}

	private List<Todo> toTodoList(Collection<TodoEntity> todos) {
		return todos.stream().map(todoMapper::toTodo).collect(Collectors.toList());
	}
}
