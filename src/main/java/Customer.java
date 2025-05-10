import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Customer extends User{
    private List<Concert> bookedConcerts = new ArrayList<>();

    public Customer(String id, String name) {
        super(id, name);
    }

    /**
     * book a concert
     * @param concert concert to be booked
     */
    public void bookConcerts(Concert concert) {
        if(concert.availableSeats() > 0) {
            concert.bookTickets();
            bookedConcerts.add(concert);
            System.out.println("Successfully booked " + concert.getName());
        } else {
            System.out.println("No available seats for" + concert.getName());
        }
    }

    /**
     * cancel a booking
     * @param concert to cancel
     */
    public void cancelBooking(Concert concert) {
        if (bookedConcerts.contains(concert)) {
            bookedConcerts.remove(concert);
            concert.setBooked(concert.getBooked() - 1);
            System.out.println("Booking canceled for " + concert.getName());
        } else {
            System.out.println("You don't have booking for " + concert.getName());
        }
    }

    /**
     * view events as customer
     * @param concerts available
     */
    @Override
    public void viewEvents(List<Concert> concerts) {
        List<Concert> available = concerts.stream()
                .filter(c -> c.availableSeats() > 0)
                .collect(Collectors.toList());

        System.out.println("Available Concerts (" + available.size() + "):");
        available.forEach(c -> {
            System.out.println("- " + c.getName() +
                    " | Date: " + c.getDate() +
                    " | Venue: " + c.getVenue() +
                    " | Available Seats: " + c.availableSeats());
        });
    }

    /**
     * lets user see his own bookings
     */
    public void viewMyBookings() {
        System.out.println("Your bookings: ");
        if (bookedConcerts.isEmpty()) {
            System.out.println("No bookings.");
        } else {
            bookedConcerts.forEach(System.out::println);
        }
    }

    public List<Concert> getBookedConcerts() {
        return bookedConcerts;
    }
}
