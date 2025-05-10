import java.util.List;

public class Admin extends User{

    public Admin(String id, String name) {
        super(id, name);
    }

    /**
     * add concert
     * @param concerts list of concerts
     * @param concert concert to be added
     */
    public void addConcert(List<Concert> concerts, Concert concert) {
        concerts.add(concert);
    }

    /**
     * remove  a concert
     * @param concerts list of concerts
     * @param concert concert to be removed
     */
    public void removeConcert(List<Concert> concerts, Concert concert) {
        concerts.remove(concert);
    }

    /**
     * view event as admin
     * @param concerts shown
     */
    @Override
    public void viewEvents(List<Concert> concerts) {
        System.out.println("\nAll Concerts (" + concerts.size() + "):");
        concerts.forEach(c -> {
            System.out.println("- " + c.getName() +
                    " | Date: " + c.getDate() +
                    " | Venue: " + c.getVenue() +
                    " | Booked: " + c.getBooked() + "/" + c.getSeats());
        });
    }

    /**
     * view all bookings
     * @param concerts list of concerts
     */
    public void viewAllBookings(List<Concert> concerts) {
        for (Concert concert : concerts) {
            System.out.println(concert.getName() + " - Booked " + concert.getBooked() + "/"  + concert.getSeats());
        }
    }
}
