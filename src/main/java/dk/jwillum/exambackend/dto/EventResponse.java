package dk.jwillum.exambackend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dk.jwillum.exambackend.entity.Event;
import dk.jwillum.exambackend.entity.Location;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class EventResponse {

  private int id;
  private String name;
  private LocalDate date;
  private String description;
  private int capacity;
  private LocationResponse location;
  @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
  private LocalDateTime created;
  @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
  private LocalDateTime lastEdited;

  public EventResponse(Event e, boolean includeAll, boolean includeLocation) {
    this.id = e.getId();
    this.name = e.getName();
    this.date = e.getDate();
    this.description = e.getDescription();
    this.capacity = e.getCapacity();
    if (includeLocation) {
      this.location = new LocationResponse(e.getLocation(), includeAll, false);
    }
    if (includeAll) {
      this.created = e.getCreated();
      this.lastEdited = e.getLastEdited();
    }
  }
}
