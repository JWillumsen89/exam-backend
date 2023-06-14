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
public class Location {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "name")
  private String name;

  @Column(name = "capacity")
  private int capacity;

  @Column(name = "created")
  @CreationTimestamp
  private LocalDateTime created;

  @Column(name = "last_edited")
  @UpdateTimestamp
  private LocalDateTime lastEdited;

  //TODO add relation to Event

  @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
  private List<Event> events;


  public Location(int id, String name, int capacity, LocalDateTime created, LocalDateTime lastEdited) {
    this.id = id;
    this.name = name;
    this.capacity = capacity;
    this.created = created;
    this.lastEdited = lastEdited;
  }
}
