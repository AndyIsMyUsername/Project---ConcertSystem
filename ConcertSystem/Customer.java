package ConcertSystem;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User{
    private List<Concert> bookedConcerts = new ArrayList<>();

    public Customer(String id, String name) {
        super(id, name);
    }

    public void bookConcerts(Concert concert) {

    }

    @Override
    public void viewEvents() {
        //TODO
    }

    public List<Concert> getBookedConcerts() {
        return bookedConcerts;
    }
}
