package fr.themanis.todolistrest.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import fr.themanis.todolistrest.dtos.TodoDTO;
import fr.themanis.todolistrest.models.TodoModel;
import fr.themanis.todolistrest.models.TodoServiceException;
import fr.themanis.todolistrest.services.TodoService;

@RestController
@CrossOrigin(origins = { "http://localhost:4200" })
public class TodoController {

  @Autowired
  private TodoService todoService;

  @GetMapping(value = {"/todos", "/todos/"})
  public List<TodoDTO> getAll(@RequestParam Optional<String> keyword) throws TodoServiceException {
    List<TodoModel> models;
    if (keyword.isEmpty())
      models = this.todoService.findAll();
    else
      models = this.todoService.search(keyword.get());
    return models.stream().map(m -> toDTO(m)).collect(Collectors.toList());
  }

  @GetMapping(value = "/todos/{id:[1-9]\\d{0,5}}")
  public ResponseEntity<TodoDTO> getOneById(@PathVariable Integer id) {
    try {
      return new ResponseEntity<>(toDTO(this.todoService.findOne(id)), HttpStatus.OK);
    } catch (TodoServiceException tse) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  private TodoDTO toDTO(TodoModel model) {
    return new TodoDTO(model.getId(), model.getLabel(), model.getDueDate(), model.isDone(), model.isRemovable(),
        model.getPosition() != null ? model.getPosition().getLatitude() : null,
        model.getPosition() != null ? model.getPosition().getLongitude() : null);
  }
}
