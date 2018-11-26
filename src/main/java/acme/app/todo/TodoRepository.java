package acme.app.todo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

interface TodoRepository extends JpaRepository<TodoEntity, Long> {
	
	List<TodoEntity> findByTitleLike(String titlePattern);

}
