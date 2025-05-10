import java.time.LocalDate;
import java.util.Comparator;

public class Concert extends Event implements Bookable, Comparable<Concert> {
    private int seats;
    public int booked;

    public Concert(String name, LocalDate date, String venue, int seats) {
        super(name, date, venue);
        this.seats = seats;
        this.booked = 0;
    }

    /**
     * allows the system to book the tickets
     */
    @Override
    public void bookTickets() {
       if (availableSeats() <= 0) {
           throw new IllegalStateException("No available seats for : " + name);
       }
       booked++;
    }

    /**
     * sorts concerts by date
     * @param o the object to be compared.
     * @return
     */
    @Override
    public int compareTo(Concert o) {
        return this.date.compareTo(o.date);
    }

    /**
     * calculates available seats
     * @return seats available
     */
    @Override
    public int availableSeats() {
        return seats - booked;
    }

    /**
     * decreases the seat count
     */
    @Override
    public void decreaseSeatCount() {
        bookTickets();
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

class PopularityComparator implements Comparator<Concert> {
    @Override
    public int compare(Concert o1, Concert o2) {
        return Integer.compare(o2.getBooked(), o1.getBooked());
    }
}
