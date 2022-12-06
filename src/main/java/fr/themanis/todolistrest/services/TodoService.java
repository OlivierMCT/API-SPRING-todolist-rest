package fr.themanis.todolistrest.services;

import java.util.List;

import fr.themanis.todolistrest.models.TodoModel;
import fr.themanis.todolistrest.models.TodoServiceException;

public interface TodoService {
  List<TodoModel> getAll();
  List<TodoModel> search(String keyword) throws TodoServiceException;
  TodoModel getOne(int id) throws TodoServiceException;
}
