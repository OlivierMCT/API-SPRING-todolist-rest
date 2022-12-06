package fr.themanis.todolistrest.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.themanis.todolistrest.entities.TodoEntity;

@Repository()
public interface TodoRepository extends JpaRepository<TodoEntity, Integer> {  
  List<TodoEntity> findByLabelContaining(String keyword);
}
