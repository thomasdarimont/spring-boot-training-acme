package acme.app.todo;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import acme.app.todo.Todo;
import acme.app.todo.TodoEntity;
import acme.app.todo.TodoRepository;
import acme.app.todo.TodoService;

@ExtendWith(SpringExtension.class)
@ImportAutoConfiguration
@ComponentScan(basePackageClasses = TodoService.class)
class Example030_DefaultTodoServiceTest {

	@MockBean
	private TodoRepository todoRepository;

	@BeforeEach
	public void setup() {
		when(todoRepository.save(any(TodoEntity.class)))
				.thenAnswer(inv -> new TodoEntity(1L, ((TodoEntity) inv.getArgument(0)).getTitle()));
	}

	@Test
	void shouldCreateEntity(@Autowired TodoService todoService) {

		Todo todo = todoService.create(new Todo("Prepare training"));

		assertThat(todo).isNotNull();
		assertThat(todo.getId()).isNotNull();
	}

	@Test
	void shouldReturnEmptyListForFindAll(@Autowired TodoService todoService) {
		assertThat(todoService.findAll()).isEmpty();
	}

	@Test
	void shouldReturnListWithOneEntryForFindAll(@Autowired TodoService todoService) {

		Todo todo = new Todo("Prepare training");

		when(todoRepository.findAll()).thenReturn(singletonList(new TodoEntity(todo.getTitle())));

		assertThat(todoService.findAll()) //
				.isNotEmpty() //
				.hasSize(1) //
				.contains(todo) //
		;
	}
}
