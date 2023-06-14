package dk.jwillum.exambackend.repository;

import dk.jwillum.exambackend.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

//TODO Change name of this repo and what class it uses.
public interface EventRepository extends JpaRepository<Event, Integer> {
}
