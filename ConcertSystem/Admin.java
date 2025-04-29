package ConcertSystem;

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
    public void RemoveConcert(List<Concert> concerts, Concert concert) {
        concerts.remove(concert);
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

    @Override
    public void viewEvents() {
        //TODO
    }


}
