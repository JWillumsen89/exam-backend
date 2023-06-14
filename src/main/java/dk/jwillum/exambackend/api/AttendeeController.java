package dk.jwillum.exambackend.api;

import dk.jwillum.exambackend.dto.AttendeeRequest;
import dk.jwillum.exambackend.dto.AttendeeResponse;
import dk.jwillum.exambackend.dto.EventAttendeeResponse;
import dk.jwillum.exambackend.service.AttendeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/attendees")
public class AttendeeController {

  AttendeeService attendeeService;

  public AttendeeController(AttendeeService attendeeService) {
    this.attendeeService = attendeeService;
  }

  @PostMapping
  AttendeeResponse createAttendee(@RequestBody AttendeeRequest attendeeRequest) {
    return attendeeService.createAttendee(attendeeRequest);
  }

  @GetMapping
  List<AttendeeResponse> getAllAttendees() {
    return attendeeService.getAllAttendees(false);
  }

}
