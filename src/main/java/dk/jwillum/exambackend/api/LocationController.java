package dk.jwillum.exambackend.api;

import dk.jwillum.exambackend.dto.LocationResponse;
import dk.jwillum.exambackend.service.LocationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/location")
public class LocationController {

  LocationService locationService;

  public LocationController(LocationService locationService) {
    this.locationService = locationService;
  }

  @GetMapping("details/{id}")
  LocationResponse findLocationDetails(@PathVariable int id) {
    return locationService.getLocationById(id, true);
  }

  @GetMapping("/{includeEvents}")
  List<LocationResponse> findAllLocations(@PathVariable boolean includeEvents) {
    return locationService.getAllLocations(includeEvents);
  }
}
