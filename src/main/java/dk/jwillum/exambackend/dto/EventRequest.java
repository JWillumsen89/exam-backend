package dk.jwillum.exambackend.dto;

import dk.jwillum.exambackend.entity.Event;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class EventRequest {

  private int id;
  private String name;
  private LocalDate date;
  private String description;
  private int capacity;
  private LocalDateTime created;
  private LocalDateTime lastEdited;

  public static Event getEventEntity(EventRequest er) {
    return new Event(er.id, er.name, er.date, er.description, er.capacity, er.created, er.lastEdited);
  }

  public EventRequest(int id, String name, LocalDate date, String description, int capacity, LocalDateTime created, LocalDateTime lastEdited) {
    this.id = id;
    this.name = name;
    this.date = date;
    this.description = description;
    this.capacity = capacity;
    this.created = created;
    this.lastEdited = lastEdited;
  }

  public EventRequest(Event e) {
    this.id = e.getId();
    this.name = e.getName();
    this.date = e.getDate();
    this.description = e.getDescription();
    this.capacity = e.getCapacity();
    this.created = e.getCreated();
    this.lastEdited = e.getLastEdited();
  }


}
