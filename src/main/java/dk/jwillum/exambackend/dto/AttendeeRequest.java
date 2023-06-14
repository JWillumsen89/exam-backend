package dk.jwillum.exambackend.dto;

import dk.jwillum.exambackend.entity.Attendee;
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
public class AttendeeRequest {

  private int id;
  private String username;
  private String email;
  private String phoneNumber;
  private LocalDateTime created;
  private LocalDateTime lastEdited;

  public static Attendee getAttendeeEntity(AttendeeRequest ar) {
    return new Attendee(ar.id,ar.username,ar.email,ar.phoneNumber,ar.created,ar.lastEdited);
  }

  public AttendeeRequest(int id, String username, String email, String phoneNumber, LocalDateTime created, LocalDateTime lastEdited) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.created = created;
    this.lastEdited = lastEdited;
  }

  public AttendeeRequest(Attendee a) {
    this.id = a.getId();
    this.username = a.getUsername();
    this.email = a.getEmail();
    this.phoneNumber = a.getPhoneNumber();
    this.created = a.getCreated();
    this.lastEdited = a.getLastEdited();
  }
}
