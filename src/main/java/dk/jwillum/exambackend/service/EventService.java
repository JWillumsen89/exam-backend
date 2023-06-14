package dk.jwillum.exambackend.service;

import dk.jwillum.exambackend.dto.EventRequest;
import dk.jwillum.exambackend.dto.EventResponse;
import dk.jwillum.exambackend.entity.Event;
import dk.jwillum.exambackend.entity.Location;
import dk.jwillum.exambackend.repository.EventAttendeeRepository;
import dk.jwillum.exambackend.repository.EventRepository;
import dk.jwillum.exambackend.repository.LocationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

import static dk.jwillum.exambackend.dto.EventRequest.getEventEntity;

@org.springframework.stereotype.Service
public class EventService {

  EventRepository eventRepository;
  LocationRepository locationRepository;

  EventAttendeeRepository eventAttendeeRepository;

  public EventService(EventRepository eventRepository, LocationRepository locationRepository, EventAttendeeRepository eventAttendeeRepository) {
    this.eventRepository = eventRepository;
    this.locationRepository = locationRepository;
    this.eventAttendeeRepository = eventAttendeeRepository;
  }

  public EventResponse createEvent(EventRequest eventRequest) {
    Location location = locationRepository.findById(eventRequest.getLocation().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No location with ID: " + eventRequest.getLocation().getId()));
    if (eventRequest.getCapacity() > location.getCapacity()) {
      throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Event capacity of " + eventRequest.getCapacity() + " exceeds location's capacity. Please select a location that can accommodate your event's size.");
    } else {
      eventRequest.setLocation(location);
      Event newEvent = getEventEntity(eventRequest);
      newEvent = eventRepository.save(newEvent);
      return new EventResponse(newEvent, true, true);
    }
  }

  public List<EventResponse> getAllEvents(boolean includeAll) {
    return eventRepository.findAll().stream().map(event -> new EventResponse(event, includeAll, true)).toList();
  }

  public EventResponse getEventById(int id, boolean includeAll) {
    return eventRepository.findById(id).map(event -> new EventResponse(event, includeAll, true)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No event found with ID: " + id));
  }

  public EventResponse updateEvent(int id, EventRequest eventRequest) {
    Location location = locationRepository.findById(eventRequest.getLocation().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No location with that id"));
    Event event = eventRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No event found with ID: " + id));
    if (eventRequest.getCapacity() > location.getCapacity()) {
      throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Event capacity of " + eventRequest.getCapacity() + " exceeds location's capacity. Please select a location that can accommodate your event's size.");
    } else {
      event.setName(eventRequest.getName());
      event.setDate(eventRequest.getDate());
      event.setDescription(eventRequest.getDescription());
      event.setCapacity(eventRequest.getCapacity());
      event.setLocation(location);
      eventRepository.save(event);
      return new EventResponse(event, true, true);
    }
  }

  public List<EventResponse> findEventByName(String eventName, boolean includeAll) {
    List<Event> events = eventRepository.findByName(eventName).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no events named: " + eventName));
    if (events.size() == 0) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no events named: " + eventName);
    }
    return events.stream().map(event -> new EventResponse(event, includeAll, true)).toList();
  }

  public List<EventResponse> findEventByPartsOfName(String event, boolean includeAll) {
    List<Event> events = eventRepository.findByNameContainingIgnoreCase(event);

    if (events.isEmpty()) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, "There are no events with a name containing: " + event);
    }

    return events.stream().map(evt -> new EventResponse(evt, includeAll, true)).collect(Collectors.toList());

  }

  public void deleteEvent(int id) {
    try {
      eventRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event with ID: " + id + " not found"));
      eventAttendeeRepository.deleteByEventId(id);
      eventRepository.deleteById(id);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to delete the event. Please try again later.");
    }
  }
}
