package dk.jwillum.exambackend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dk.jwillum.exambackend.entity.Location;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class LocationResponse {
  private int id;
  private String name;
  private int capacity;
  private List<EventResponse> events;
  @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
  private LocalDateTime created;
  @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
  private LocalDateTime lastEdited;

  public LocationResponse(Location l, boolean includeAll, boolean includeEvents) {
    this.id = l.getId();
    this.name = l.getName();
    this.capacity = l.getCapacity();
    if (includeEvents) {
      this.events = l.getEvents().stream().map(event -> new EventResponse(event, true, false)).toList();
    }
    if (includeAll) {
      this.created = l.getCreated();
      this.lastEdited = l.getLastEdited();
    }
  }
}
