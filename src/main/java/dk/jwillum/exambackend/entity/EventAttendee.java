package dk.jwillum.exambackend.entity;

import jakarta.persistence.*;
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
@jakarta.persistence.Entity
public class EventAttendee {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "register_date")
  private LocalDate registerDate;

  @Column(name = "created")
  @CreationTimestamp
  private LocalDateTime created;

  @Column(name = "last_edited")
  @UpdateTimestamp
  private LocalDateTime lastEdited;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "attendee_id")
  private Attendee attendee;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "event_id")
  private Event event;

//Todo fix it
  public EventAttendee(int id, LocalDate registerDate, LocalDateTime created, LocalDateTime lastEdited) {
    this.id = id;
    this.registerDate = registerDate;
    this.created = created;
    this.lastEdited = lastEdited;
  }

  public EventAttendee(int id, LocalDate registerDate, Attendee attendee, Event event) {
    this.id = id;
    this.registerDate = registerDate;
    this.attendee = attendee;
    this.event = event;
  }

  public EventAttendee(int id, LocalDate registerDate, LocalDateTime created, LocalDateTime lastEdited, Attendee attendee, Event event) {
    this.id = id;
    this.registerDate = registerDate;
    this.created = created;
    this.lastEdited = lastEdited;
    this.attendee = attendee;
    this.event = event;
  }
}
