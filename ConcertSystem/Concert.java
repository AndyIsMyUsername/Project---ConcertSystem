package ConcertSystem;

import java.time.LocalDate;

public class Concert extends Event implements Comparable<Concert> {
    private int seats;
    private int booked;

    public Concert(String name, LocalDate date, String venue, int seats) {
        super(name, date, venue);
        this.seats = seats;
        this.booked = 0;
    }

    @Override
    public int availableSeats() {
        return seats - booked;
    }

    @Override
    public void decreaseSeatCount() {
        if (seats > 0) {
            seats--;
        }
    }

    @Override
    public int compareTo(Concert o) {
        return this.date.compareTo(o.date);
    }

    @Override
    public String toString() {
        return "Concert{" +
                "name='" + name + '\'' +
                ", date=" + date +
                ", venue='" + venue + '\'' +
                "," + availableSeats()  + "seats left" +
                '}';
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public int getBooked() {
        return booked;
    }

    public void setBooked(int booked) {
        this.booked = booked;
    }
}
