package dk.jwillum.exambackend.service;

import dk.jwillum.exambackend.entity.Attendee;
import dk.jwillum.exambackend.repository.AttendeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AttendeeServiceTest {

  @Autowired
  AttendeeRepository attendeeRepository;

  @BeforeEach
  void setUp() {
    createAttendees();
  }

  public void createAttendees() {
    List<Attendee> attendees = new ArrayList<>();

    attendees.add(new Attendee("JohnDoe", "johndoe@example.com", "1234567890"));
    attendees.add(new Attendee("JaneSmith", "janesmith@example.com", "9876543210"));
    attendees.add(new Attendee("DavidWilson", "davidwilson@example.com", "4567891230"));
    attendees.add(new Attendee("EmilyJohnson", "emilyjohnson@example.com", "0123456789"));
    attendees.add(new Attendee("MichaelBrown", "michaelbrown@example.com", "7890123456"));
    attendees.add(new Attendee("SarahTaylor", "sarahtaylor@example.com", "3456789012"));
    attendees.add(new Attendee("RobertDavis", "robertdavis@example.com", "8901234567"));
    attendees.add(new Attendee("OliviaMoore", "oliviamoore@example.com", "5678901234"));
    attendees.add(new Attendee("WilliamAnderson", "williamanderson@example.com", "9012345678"));
    attendees.add(new Attendee("SophiaJackson", "sophiajackson@example.com", "2345678901"));
    attendees.add(new Attendee("JamesMiller", "jamesmiller@example.com", "6789012345"));
    attendees.add(new Attendee("AvaHarris", "avaharris@example.com", "1234567890"));
    attendees.add(new Attendee("BenjaminClark", "benjaminclark@example.com", "9876543210"));
    attendees.add(new Attendee("MiaLewis", "mialewis@example.com", "4567891230"));
    attendees.add(new Attendee("HenryWalker", "henrywalker@example.com", "0123456789"));
    attendees.add(new Attendee("EllaHall", "ellahall@example.com", "7890123456"));
    attendees.add(new Attendee("AlexanderTurner", "alexanderturner@example.com", "3456789012"));
    attendees.add(new Attendee("SofiaCarter", "sofiacarter@example.com", "8901234567"));
    attendees.add(new Attendee("JackWilson", "jackwilson@example.com", "5678901234"));
    attendees.add(new Attendee("AveryGreen", "averygreen@example.com", "9012345678"));
    attendees.add(new Attendee("LiamMoore", "liammoore@example.com", "2345678901"));
    attendees.add(new Attendee("EmmaAllen", "emmaallen@example.com", "6789012345"));
    attendees.add(new Attendee("SebastianHill", "sebastianhill@example.com", "1234567890"));
    attendees.add(new Attendee("CharlotteYoung", "charlotteyoung@example.com", "9876543210"));
    attendees.add(new Attendee("LucasScott", "lucasscott@example.com", "4567891230"));

    attendeeRepository.saveAll(attendees);
    attendeeRepository.flush();
  }

  @Test
  void createAttendee() {
    Attendee attendee = new Attendee();
    attendee.setUsername("Test");
    attendee.setEmail("t@t.dk");
    attendee.setPhoneNumber("12345678");
    assertNotNull(attendeeRepository.save(attendee));
  }

  @Test
  void getAllAttendees() {
    List<Attendee> list = attendeeRepository.findAll();
    assertEquals(25, list.size());
  }
}