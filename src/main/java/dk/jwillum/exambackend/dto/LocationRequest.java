package dk.jwillum.exambackend.dto;

import dk.jwillum.exambackend.entity.Location;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class LocationRequest {
  private int id;
  private String name;
  private int capacity;
  private LocalDateTime created;
  private LocalDateTime lastEdited;

  public static Location getLocationEntity(LocationRequest lr) {
    return new Location(lr.id,lr.name,lr.capacity,lr.created,lr.lastEdited);
  }

  public LocationRequest(int id, String name, int capacity, LocalDateTime created, LocalDateTime lastEdited) {
    this.id = id;
    this.name = name;
    this.capacity = capacity;
    this.created = created;
    this.lastEdited = lastEdited;
  }

  public LocationRequest(Location l) {
    this.id = l.getId();
    this.name = l.getName();
    this.capacity = l.getCapacity();
    this.created = l.getCreated();
    this.lastEdited = l.getLastEdited();
  }
}
