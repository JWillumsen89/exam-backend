package dk.jwillum.exambackend.dto;

import dk.jwillum.exambackend.entity.EventAttendee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class EventAttendeeRequest {

  private int id;
  private LocalDate registerDate;
  private LocalDateTime created;
  private LocalDateTime lastEdited;

  public static EventAttendee getEventAttendeeEntity(EventAttendeeRequest ear) {
    return new EventAttendee(ear.id, ear.registerDate,ear.created,ear.lastEdited);
  }

  public EventAttendeeRequest(int id, LocalDate registerDate, LocalDateTime created, LocalDateTime lastEdited) {
    this.id = id;
    this.registerDate = registerDate;
    this.created = created;
    this.lastEdited = lastEdited;
  }

  public EventAttendeeRequest(EventAttendee ea) {
    this.id = ea.getId();
    this.registerDate = ea.getRegisterDate();
    this.created = ea.getCreated();
    this.lastEdited = ea.getLastEdited();
  }
}
