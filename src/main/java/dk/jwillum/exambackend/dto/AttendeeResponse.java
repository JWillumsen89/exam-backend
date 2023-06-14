package dk.jwillum.exambackend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dk.jwillum.exambackend.entity.Attendee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class AttendeeResponse {

  private int id;
  private String username;
  private String email;
  private String phoneNumber;
  @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
  private LocalDateTime created;
  @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
  private LocalDateTime lastEdited;

  public AttendeeResponse(Attendee a, boolean includeAll) {
    this.id = a.getId();
    this.username = a.getUsername();
    this.email = a.getEmail();
    this.phoneNumber = a.getPhoneNumber();
    if (includeAll) {
      this.created = a.getCreated();
      this.lastEdited = a.getLastEdited();
    }
  }
}
