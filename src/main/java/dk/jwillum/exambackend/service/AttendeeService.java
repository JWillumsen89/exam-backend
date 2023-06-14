package dk.jwillum.exambackend.service;

import dk.jwillum.exambackend.dto.AttendeeRequest;
import dk.jwillum.exambackend.dto.AttendeeResponse;
import dk.jwillum.exambackend.entity.Attendee;
import dk.jwillum.exambackend.repository.AttendeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static dk.jwillum.exambackend.dto.AttendeeRequest.getAttendeeEntity;

@Service
public class AttendeeService {

  AttendeeRepository attendeeRepository;

  public AttendeeService(AttendeeRepository attendeeRepository) {
    this.attendeeRepository = attendeeRepository;
  }

  public AttendeeResponse createAttendee(AttendeeRequest attendeeRequest) {
    Attendee newAttendee = getAttendeeEntity(attendeeRequest);
    newAttendee = attendeeRepository.save(newAttendee);
    return new AttendeeResponse(newAttendee,false);
  }

  public List<AttendeeResponse> getAllAttendees(boolean includeALl) {
    return attendeeRepository.findAll().stream().map(attendee -> new AttendeeResponse(attendee,includeALl)).toList();
  }
}
