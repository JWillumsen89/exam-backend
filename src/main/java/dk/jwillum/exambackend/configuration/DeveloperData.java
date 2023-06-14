package dk.jwillum.exambackend.configuration;

import dk.jwillum.exambackend.entity.Location;
import dk.jwillum.exambackend.repository.AttendeeRepository;
import dk.jwillum.exambackend.repository.EventAttendeeRepository;
import dk.jwillum.exambackend.repository.EventRepository;
import dk.jwillum.exambackend.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DeveloperData implements ApplicationRunner {

  @Autowired
  AttendeeRepository attendeeRepository;
  @Autowired
  EventAttendeeRepository eventAttendeeRepository;
  @Autowired
  EventRepository eventRepository;
  @Autowired
  LocationRepository locationRepository;

  @Override
  public void run(ApplicationArguments args) {
    createLocations();
  }

  public void createLocations() {
    Location loc1 = new Location("Test Location 1", 500);
    locationRepository.save(loc1);
    Location loc2 = new Location("Test Location 2", 1000);
    locationRepository.save(loc2);
  }

  public void createEvent() {

  }
}
