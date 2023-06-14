package dk.jwillum.exambackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@jakarta.persistence.Entity

public class Event {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "name")
  private String name;

  @Column(name = "date")
  private LocalDate date;

  @Column(name = "description")
  private String description;

  @Column(name = "capacity")
  private int capacity;

  @Column(name = "created")
  @CreationTimestamp
  private LocalDateTime created;

  @Column(name = "last_edited")
  @UpdateTimestamp
  private LocalDateTime lastEdited;

  @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
  private List<EventAttendee> eventAttendees;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "location_id")
  private Location location;

  public Event(int id, String name, LocalDate date, String description, int capacity, LocalDateTime created, LocalDateTime lastEdited) {
    this.id = id;
    this.name = name;
    this.date = date;
    this.description = description;
    this.capacity = capacity;
    this.created = created;
    this.lastEdited = lastEdited;
  }
}
