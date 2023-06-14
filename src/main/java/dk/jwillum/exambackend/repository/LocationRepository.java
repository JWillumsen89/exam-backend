package dk.jwillum.exambackend.repository;

import dk.jwillum.exambackend.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Integer> {
}
