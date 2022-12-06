package fr.themanis.todolistrest.models;

public class TodoServiceException extends Exception {
  private int code;
  public int getCode() { return code; }

  public TodoServiceException() {}

  public TodoServiceException(int code, String message) {
    super(message);
    this.code = code;
  }

  public TodoServiceException(Throwable cause) {
    super(cause);
  }

  public TodoServiceException(int code, String message, Throwable cause) {
    super(message, cause);
    this.code = code;
  }

}
