package dk.jwillum.exambackend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dk.jwillum.exambackend.entity.Attendee;
import dk.jwillum.exambackend.entity.EventAttendee;
import jakarta.persistence.Column;
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
public class EventAttendeeResponse {

  private int id;
  private LocalDate registerDate;
  private AttendeeResponse attendeeResponse;
  private EventResponse eventResponse;
  @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
  private LocalDateTime created;
  @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
  private LocalDateTime lastEdited;

  public EventAttendeeResponse(EventAttendee ea, boolean includeAll) {
    this.id = ea.getId();
    this.registerDate = ea.getRegisterDate();
    this.attendeeResponse = new AttendeeResponse(ea.getAttendee(),true);
    this.eventResponse = new EventResponse(ea.getEvent(),true,true);
    if (includeAll) {
      this.created = ea.getCreated();
      this.lastEdited = ea.getLastEdited();
    }
  }
}
