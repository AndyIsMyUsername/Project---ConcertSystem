import java.util.List;
import java.util.stream.Collectors;

public abstract class User {
    private String id;
    private String name;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * abstract method allowing for both admin and customer to view events
     */
    public void viewEvents(List<Concert> concerts) {
        System.out.println("All events: ");
        concerts.forEach(c-> {
            System.out.println("- " + c.getName() +
                    " | Date: " + c.getDate() +
                    " | Venue: " + c.getVenue() +
                    " | Available Seats: " + c.availableSeats());
        });
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
