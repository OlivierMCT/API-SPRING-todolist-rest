package fr.themanis.todolistrest.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import fr.themanis.todolistrest.models.TodoServiceException;

@ControllerAdvice
public class TodoAdviceController {

  @ExceptionHandler(TodoServiceException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public String generateErrorMessageForTodoServiceException(TodoServiceException tse){
    return tse.getMessage();
  }
}
