package dk.jwillum.exambackend.service;

import dk.jwillum.exambackend.dto.EventAttendeeRequest;
import dk.jwillum.exambackend.dto.EventAttendeeResponse;
import dk.jwillum.exambackend.entity.Attendee;
import dk.jwillum.exambackend.entity.Event;
import dk.jwillum.exambackend.entity.EventAttendee;
import dk.jwillum.exambackend.repository.AttendeeRepository;
import dk.jwillum.exambackend.repository.EventAttendeeRepository;
import dk.jwillum.exambackend.repository.EventRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static dk.jwillum.exambackend.dto.EventAttendeeRequest.getEventAttendeeEntity;

@Service
public class EventAttendeeService {

  EventAttendeeRepository eventAttendeeRepository;
  AttendeeRepository attendeeRepository;
  EventRepository eventRepository;

  public EventAttendeeService(EventAttendeeRepository eventAttendeeRepository, AttendeeRepository attendeeRepository, EventRepository eventRepository) {
    this.eventAttendeeRepository = eventAttendeeRepository;
    this.attendeeRepository = attendeeRepository;
    this.eventRepository = eventRepository;
  }

  public EventAttendeeResponse signUpForEvent(EventAttendeeRequest eventAttendeeRequest) {

    Attendee attendee = attendeeRepository.findById(eventAttendeeRequest.getAttendee().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No attendee with id: " + eventAttendeeRequest.getAttendee().getId()));
    Event event = eventRepository.findById(eventAttendeeRequest.getEvent().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No event with ID: " + eventAttendeeRequest.getEvent().getId()));

    if (alreadySignedUp(attendee.getId(),event.getId())) {
      throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Attendee is already signed up for this event");
    }

    int countForEvent = getNumberOfAttendeesForEvent(event.getId());

    if (countForEvent == event.getCapacity()) {
      throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "The max capacity for this event is reached.");
    }

    eventAttendeeRequest.setAttendee(attendee);
    eventAttendeeRequest.setEvent(event);
    EventAttendee newEventAttendee = getEventAttendeeEntity(eventAttendeeRequest);
    newEventAttendee = eventAttendeeRepository.save(newEventAttendee);
    return new EventAttendeeResponse(newEventAttendee, false);
  }

  public int getNumberOfAttendeesForEvent(int id) {
    List<EventAttendee> attendees = eventAttendeeRepository.findByEventId(id);
    if (attendees == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No attendees found for event with ID: " + id);
    }
    return attendees.size();
  }

  public boolean alreadySignedUp(int attendeeId, int eventId) {
    Optional<EventAttendee> eventAttendee = eventAttendeeRepository.findByAttendeeIdAndEventId(attendeeId, eventId);
    return eventAttendee.isPresent();
  }


  public void deleteEventAttendee(int attendeeId, int eventId) {
    Optional<EventAttendee> eventAttendee = eventAttendeeRepository.findByAttendeeIdAndEventId(attendeeId, eventId);
    if (eventAttendee.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No EventAttendee found with Attendee ID: " + attendeeId + " and Event ID: " + eventId);
    }
    eventAttendeeRepository.deleteByAttendeeIdAndEventId(attendeeId, eventId);
  }

  public List<EventAttendeeResponse> getAllEventAttendees() {
    return eventAttendeeRepository.findAll().stream().map(eventAttendee -> new EventAttendeeResponse(eventAttendee, true)).toList();
  }
}