package dk.jwillum.exambackend.service;

import dk.jwillum.exambackend.entity.Location;
import dk.jwillum.exambackend.repository.LocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class LocationServiceTest {

  @Autowired
  LocationRepository locationRepository;

  private Location testLocation;

  @BeforeEach
  void setUp() {
    testLocation = createLocation();
  }
  private Location createLocation() {
    Location location = new Location();
    location.setName("test location");
    location.setCapacity(500);
    return locationRepository.save(location);
  }

  @Test
  void getLocationById() {
    Optional<Location> location = locationRepository.findById(testLocation.getId());
    assertNotNull(location);
  }

  @Test
  void getAllLocations() {
    List<Location> locations = locationRepository.findAll();
    assertEquals(1,locations.size());
  }
}