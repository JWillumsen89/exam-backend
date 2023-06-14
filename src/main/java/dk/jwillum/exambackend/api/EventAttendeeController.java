package dk.jwillum.exambackend.api;

import dk.jwillum.exambackend.dto.EventAttendeeRequest;
import dk.jwillum.exambackend.dto.EventAttendeeResponse;
import dk.jwillum.exambackend.entity.EventAttendee;
import dk.jwillum.exambackend.service.EventAttendeeService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/eventAttendees")
public class EventAttendeeController {

  EventAttendeeService eventAttendeeService;

  public EventAttendeeController(EventAttendeeService eventAttendeeService) {
    this.eventAttendeeService = eventAttendeeService;
  }

  @PostMapping
  EventAttendeeResponse signUpForEvent(@RequestBody EventAttendeeRequest eventAttendeeRequest) {
    return eventAttendeeService.signUpForEvent(eventAttendeeRequest);
  }
}
