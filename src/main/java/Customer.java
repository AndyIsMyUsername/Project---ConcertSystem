import java.util.ArrayList;
import java.util.List;

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
            System.out.println("Successfully booked" + concert.getName());
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
            concert.setSeats(concert.getSeats() + 1);
            concert.setBooked(concert.getSeats() - 1);
            System.out.println("Booking canceled for " + concert.getName());
        } else {
            System.out.println("You don't have booking for " + concert.getName());
        }
    }

    @Override
    public void viewEvents(List<Concert> concerts) {
        System.out.println("Available concerts: ");
        concerts.forEach(System.out::println);
    }

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
