package dk.jwillum.exambackend.service;

import dk.jwillum.exambackend.dto.LocationResponse;
import dk.jwillum.exambackend.repository.LocationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LocationService {

  LocationRepository locationRepository;

  public LocationService(LocationRepository locationRepository) {
    this.locationRepository = locationRepository;
  }

  public LocationResponse getLocationById(int id, boolean includeAllDetails) {
    return locationRepository.findById(id).map(location -> new LocationResponse(location, includeAllDetails, includeAllDetails)).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"No location found with ID: " + id));
  }

  public List<LocationResponse> getAllLocations(boolean includeEvents) {
    return locationRepository.findAll().stream().map(location -> new LocationResponse(location, false, includeEvents)).toList();
  }
}
