package dk.jwillum.exambackend.repository;

import dk.jwillum.exambackend.entity.EventAttendee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventAttendeeRepository extends JpaRepository<EventAttendee, Integer> {

  List<EventAttendee> findByEventId(int id);

  Optional<EventAttendee> findByAttendeeIdAndEventId(int attendeeId, int eventId);

  void deleteByAttendeeIdAndEventId(int attendeeId, int eventId);

}
