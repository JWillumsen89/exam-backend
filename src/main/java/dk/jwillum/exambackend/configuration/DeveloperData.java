package dk.jwillum.exambackend.configuration;

import dk.jwillum.exambackend.entity.Attendee;
import dk.jwillum.exambackend.entity.Event;
import dk.jwillum.exambackend.entity.Location;
import dk.jwillum.exambackend.repository.AttendeeRepository;
import dk.jwillum.exambackend.repository.EventAttendeeRepository;
import dk.jwillum.exambackend.repository.EventRepository;
import dk.jwillum.exambackend.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    createAttendees();
    createLocations();
    List<Location> locations = locationRepository.findAll();
    createEvent(locations);
  }

  public void createLocations() {
    List<Location> locations = new ArrayList<>();

    locations.add(new Location("Convention Center A", 1000));
    locations.add(new Location("Beachfront Resort B", 500));
    locations.add(new Location("Stadium C", 10000));
    locations.add(new Location("Historic Mansion D", 200));
    locations.add(new Location("Rooftop Terrace E", 300));
    locations.add(new Location("Art Gallery F", 150));
    locations.add(new Location("Conference Hall G", 800));
    locations.add(new Location("Botanical Garden H", 400));
    locations.add(new Location("Vineyard I", 250));
    locations.add(new Location("Country Club J", 600));
    locations.add(new Location("Theatre K", 1200));
    locations.add(new Location("Hotel Ballroom L", 700));
    locations.add(new Location("Outdoor Park M", 1000));
    locations.add(new Location("Museum N", 350));
    locations.add(new Location("Mansion O", 150));
    locations.add(new Location("Sports Complex P", 5000));
    locations.add(new Location("Restaurant Q", 100));
    locations.add(new Location("Banquet Hall R", 400));
    locations.add(new Location("Community Center S", 200));
    locations.add(new Location("Warehouse T", 1500));

    locationRepository.saveAll(locations);
  }

  private LocalDate getRandomFutureDate(LocalDate currentDate) {
    Random random = new Random();
    int daysToAdd = random.nextInt(365) + 1; // Random number of days between 1 and 365
    return currentDate.plusDays(daysToAdd);
  }

  public void createEvent(List<Location> locations) {
    LocalDate currentDate = LocalDate.now();
    Random random = new Random();

    String[] eventNames = {
        "Tech Expo and Conference",
        "Music Festival in the Park",
        "International Film Festival",
        "Charity Gala Dinner",
        "Business Conference and Networking",
        "Food and Wine Tasting Event",
        "Sports Tournament: Tennis Open",
        "Art Exhibition: Modern Masterpieces",
        "Technology Hackathon",
        "Fashion Show: Spring Collection",
        "Health and Wellness Expo",
        "Science Symposium: Future Technologies",
        "Comedy Show: Stand-Up Extravaganza",
        "Cultural Festival: World Heritage Celebrations",
        "Tech Startup Pitch Competition",
        "Literary Reading: An Evening with Renowned Authors",
        "Music Concert: Rock in the City",
        "Photography Workshop: Mastering Landscape Photography",
        "E-Sports Tournament: Clash of Champions",
        "Environmental Summit: Sustainability for a Better Future"
    };

    String[] eventDescriptions = {
        "Join the largest gathering of tech enthusiasts, featuring keynote speeches, product showcases, and networking opportunities.",
        "Enjoy live performances from top artists across various genres, food trucks, and a vibrant atmosphere.",
        "Celebrate the art of cinema with screenings of independent and international films, panel discussions, and awards.",
        "Support a noble cause while enjoying an elegant evening filled with fine dining, live entertainment, and fundraising activities.",
        "Connect with industry leaders, attend insightful workshops, and explore business opportunities in this dynamic event.",
        "Indulge in a culinary journey with delectable dishes, exquisite wines, and interactive chef demonstrations.",
        "Watch top-ranked tennis players compete in a thrilling tournament, showcasing their skills and athleticism.",
        "Immerse yourself in the world of contemporary art, featuring captivating paintings, sculptures, and installations.",
        "Collaborate with like-minded individuals to create innovative solutions and prototypes within a time-constrained environment.",
        "Experience the latest trends in fashion as renowned designers showcase their vibrant spring collections.",
        "Explore a wide range of health products, participate in fitness workshops, and gain valuable insights into holistic well-being.",
        "Dive into discussions on emerging scientific breakthroughs, futuristic technologies, and their potential impact on society.",
        "Laugh your heart out with a lineup of hilarious comedians delivering side-splitting performances.",
        "Immerse yourself in the richness of diverse cultures through music, dance, traditional cuisines, and artisanal crafts.",
        "Witness aspiring entrepreneurs present their groundbreaking ideas and compete for funding and support.",
        "Delve into the world of literature as acclaimed authors share excerpts from their works and engage in insightful discussions.",
        "Experience the energy of rock music as legendary bands take the stage for a night of electrifying performances.",
        "Enhance your photography skills with expert guidance, practical exercises, and breathtaking landscapes as your subjects.",
        "Witness top e-sports teams compete in intense battles across popular gaming titles, vying for glory and prize money.",
        "Engage in conversations about environmental challenges, innovative solutions, and collective actions for a sustainable world."
    };

    int[] eventCapacities = {
        1000, 5000, 300, 200, 800, 300, 1000, 150, 400, 500, 700, 300, 200, 1000, 150, 2000, 50, 500, 400, 400
    };

    List<Integer> usedLocationIndices = new ArrayList<>();

    for (int i = 0; i < 30; i++) {
      String eventName = eventNames[random.nextInt(eventNames.length)];
      LocalDate eventDate = getRandomFutureDate(currentDate);
      String eventDescription = eventDescriptions[random.nextInt(eventDescriptions.length)];
      int eventCapacity = eventCapacities[random.nextInt(eventCapacities.length)];

      int locationIndex;
      Location selectedLocation;
      do {
        locationIndex = random.nextInt(locations.size());
        selectedLocation = locations.get(locationIndex);
      } while (usedLocationIndices.contains(locationIndex) || eventCapacity > selectedLocation.getCapacity());

      usedLocationIndices.add(locationIndex);
      Event event = new Event(eventName, eventDate, eventDescription, eventCapacity, selectedLocation);
      eventRepository.save(event);
    }
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



}
