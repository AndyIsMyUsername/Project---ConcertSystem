package ConcertSystem;

import java.util.List;

public class Admin extends User{

    public Admin(String id, String name) {
        super(id, name);
    }


    public void addConcert(List<Concert> concerts, Concert concert) {

    }

    public void RemoveConcert(List<Concert> concerts, Concert concert) {

    }

    public void viewAllBookings(List<Concert> concerts) {

    }

    @Override
    public void viewEvents() {
        //TODO
    }


}
