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
    Attendee attendee = attendeeRepository.findById(eventAttendeeRequest.getAttendee().getId()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "No attendee with id: " + eventAttendeeRequest.getAttendee().getId()));
    System.out.println("Attendee object: " + attendee.getId());
    Event event = eventRepository.findById(eventAttendeeRequest.getEvent().getId()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "No event with ID: " + eventAttendeeRequest.getEvent().getId()));
    System.out.println("Event object: " + event.getId());
    eventAttendeeRequest.setAttendee(attendee);
    eventAttendeeRequest.setEvent(event);
    EventAttendee newEventAttendee = getEventAttendeeEntity(eventAttendeeRequest);
    newEventAttendee = eventAttendeeRepository.save(newEventAttendee);
    return new EventAttendeeResponse(newEventAttendee, false);
  }
}
