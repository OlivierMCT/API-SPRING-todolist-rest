package fr.themanis.todolistrest.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TodoEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false)
  private String label;
  
  @Column(nullable = false)
  private Date dueDate;
  
  @Column(nullable = false)
  private boolean done;
  
  @Column(nullable = true)
  private Double latitude;
  
  @Column(nullable = true)
  private Double longitude;

  @Column(nullable = false)
  private Date createdAt;
  @Column(nullable = false)
  private Date updatedAt;

  public TodoEntity(Integer id, String label, Date dueDate, boolean done, Double latitude, Double longitude,
      Date createdAt, Date updatedAt) {
    this.id = id;
    this.label = label;
    this.dueDate = dueDate;
    this.done = done;
    this.latitude = latitude;
    this.longitude = longitude;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }
  public TodoEntity(){}


  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
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
  public Date getCreatedAt() {
    return createdAt;
  }
  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }
  public Date getUpdatedAt() {
    return updatedAt;
  }
  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }
}
