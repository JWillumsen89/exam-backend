package dk.jwillum.exambackend.repository;

import dk.jwillum.exambackend.entity.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendeeRepository extends JpaRepository<Attendee, Integer> {
}
