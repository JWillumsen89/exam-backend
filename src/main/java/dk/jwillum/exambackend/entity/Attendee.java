package dk.jwillum.exambackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@jakarta.persistence.Entity
public class Attendee {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "username")
  private String username;

  @Column(name = "email")
  private String email;

  @Column(name = "phone_number")
  private String phoneNumber;

  @Column(name = "created")
  @CreationTimestamp
  private LocalDateTime created;

  @Column(name = "last_edited")
  @UpdateTimestamp
  private LocalDateTime lastEdited;

  @OneToMany(mappedBy = "attendee", cascade = CascadeType.ALL)
  private List<EventAttendee> eventAttendees;

  public Attendee(int id, String username, String email, String phoneNumber, LocalDateTime created, LocalDateTime lastEdited) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.created = created;
    this.lastEdited = lastEdited;
  }

  public Attendee(String username, String email, String phoneNumber) {
    this.username = username;
    this.email = email;
    this.phoneNumber = phoneNumber;
  }
}
