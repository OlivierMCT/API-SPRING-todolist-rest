package fr.themanis.todolistrest.models;

import java.util.Date;

public class TodoModel {
  private int id;
  private String label;
  private Date dueDate;
  private boolean done;
  private boolean removable;
  private TodoCoordinate position;

  public TodoModel(int id, String label, Date dueDate, boolean done, boolean removable, TodoCoordinate position) {
    this.id = id;
    this.label = label;
    this.dueDate = dueDate;
    this.done = done;
    this.removable = removable;
    this.position = position;
  }
  
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public String getLabel() {
    return label;
  }
  public void setLabel(String label) {
    this.label = label;
  }
  public Date getDueDate() {
    return dueDate;
  }
  public void setDueDate(Date dueDate) {
    this.dueDate = dueDate;
  }
  public boolean isDone() {
    return done;
  }
  public void setDone(boolean done) {
    this.done = done;
  }
  public boolean isRemovable() {
    return removable;
  }
  public void setRemovable(boolean removable) {
    this.removable = removable;
  }
  public TodoCoordinate getPosition() {
    return position;
  }
  public void setPosition(TodoCoordinate position) {
    this.position = position;
  }
}
