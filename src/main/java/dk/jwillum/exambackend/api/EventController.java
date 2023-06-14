package dk.jwillum.exambackend.api;

import dk.jwillum.exambackend.dto.EventRequest;
import dk.jwillum.exambackend.dto.EventResponse;
import dk.jwillum.exambackend.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/events")
public class EventController {

  EventService eventService;

  public EventController(EventService eventService) {
    this.eventService = eventService;
  }

  @PostMapping
  EventResponse createEvent(@RequestBody EventRequest eventRequest) {
    return eventService.createEvent(eventRequest);
  }

  @GetMapping
  List<EventResponse> getAllEvents() {
    //Without admin details
    return eventService.getAllEvents(true);
  }

  @GetMapping("/{id}")
  EventResponse getEventById(@PathVariable int id) {
    return eventService.getEventById(id, false);
  }

  @PutMapping("/{id}")
  EventResponse updateEvent(@PathVariable int id, @RequestBody EventRequest eventRequest) {
    return eventService.updateEvent(id, eventRequest);
  }

  @GetMapping("/name/{event}")
  List <EventResponse> findEventByName(@PathVariable String event) {
    return eventService.findEventByName(event, false);
  }

  @GetMapping("/partsofname/{event}")
  List <EventResponse> findEventByPartsOfName(@PathVariable String event) {
    return eventService.findEventByPartsOfName(event, false);
  }

  @DeleteMapping("/{id}")
  ResponseEntity<?> deleteEvent(@PathVariable int id) {
  eventService.deleteEvent(id);
  return ResponseEntity.ok("Event with ID " + id + " has been deleted");
  }
}
