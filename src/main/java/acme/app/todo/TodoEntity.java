package acme.app.todo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
class TodoEntity {

	@Id
	@GeneratedValue
	Long id;

	String title;

	public TodoEntity(String title) {
		this.title = title;
	}
}
