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
        }
    }

    @Override
    public void viewEvents(List<Concert> concerts) {
        concerts.forEach(System.out::println);
    }

    public List<Concert> getBookedConcerts() {
        return bookedConcerts;
    }
}
