package dk.jwillum.exambackend.repository;

import dk.jwillum.exambackend.entity.EventAttendee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventAttendeeRepository extends JpaRepository<EventAttendee, Integer> {
}
