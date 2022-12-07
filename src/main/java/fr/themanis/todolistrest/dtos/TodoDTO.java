package fr.themanis.todolistrest.dtos;

import java.util.Date;

public class TodoDTO {
  private int id;
  private String label;
  private Date dueDate;
  private boolean done;
  private boolean removable;
  private Double latitude;
  private Double longitude;

  public TodoDTO(int id, String label, Date dueDate, boolean done, boolean removable, Double latitude,
      Double longitude) {
    this.id = id;
    this.label = label;
    this.dueDate = dueDate;
    this.done = done;
    this.removable = removable;
    this.latitude = latitude;
    this.longitude = longitude;
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
  public Double getLatitude() {
    return latitude;
  }
  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }
  public Double getLongitude() {
    return longitude;
  }
  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }
}
