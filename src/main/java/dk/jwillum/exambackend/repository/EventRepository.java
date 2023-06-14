package dk.jwillum.exambackend.repository;

import dk.jwillum.exambackend.dto.EventResponse;
import dk.jwillum.exambackend.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

//TODO Change name of this repo and what class it uses.
public interface EventRepository extends JpaRepository<Event, Integer> {
  Optional<List<Event>> findByName(String event);

  List<Event> findByNameContainingIgnoreCase(String event);
}
