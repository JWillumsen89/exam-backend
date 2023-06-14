package dk.jwillum.exambackend.repository;

import dk.jwillum.exambackend.entity.Entity;
import org.springframework.data.jpa.repository.JpaRepository;

//TODO Change name of this repo and what class it uses.
public interface Repository extends JpaRepository<Entity, Integer> {
}
