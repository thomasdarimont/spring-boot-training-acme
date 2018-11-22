package acme.todo;

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
	public Todo create(Todo todo) {
		return todoMapper.toTodo(todoRepository.save(todoMapper.toTodoEntity(todo)));
	}

	@Override
	public List<Todo> findAll() {
		return todoRepository.findAll().stream().map(todoMapper::toTodo).collect(Collectors.toList());
	}
}
