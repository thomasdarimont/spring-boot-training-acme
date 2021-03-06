package acme.app.todo;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TodoMapper {

	Todo toTodo(TodoEntity entity);

	TodoEntity toTodoEntity(NewTodo todo);
	
	TodoEntity toTodoEntity(Todo todo);
}
