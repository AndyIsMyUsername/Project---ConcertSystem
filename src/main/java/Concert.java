import java.time.LocalDate;
import java.util.Comparator;

public class Concert extends Event implements Bookable, Comparable<Concert> {
    private int seats;
    private int booked;

    public Concert(String name, LocalDate date, String venue, int seats) {
        super(name, date, venue);
        this.seats = seats;
        this.booked = 0;
    }

    @Override
    public void bookTickets() {
       if (availableSeats() <= 0) {
           throw new IllegalStateException("No available seats for : " + name);
       }
       booked++;
    }

    @Override
    public int compareTo(Concert o) {
        return this.date.compareTo(o.date);
    }

    @Override
    public int availableSeats() {
        return seats - booked;
    }

    @Override
    public void decreaseSeatCount() {
        booked++;
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
