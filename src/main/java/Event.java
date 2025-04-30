import java.time.LocalDate;

public abstract class Event {
    protected String name;
    protected LocalDate date;
    protected String venue;

    public Event(String name, LocalDate date, String venue) {
        this.name = name;
        this.date = date;
        this.venue = venue;
    }

    /**
     * available seats
     * @return number of available seats
     */
    public abstract int availableSeats();

    /**
     * by booking a seat, automatically decrease seat available by one
     */
    public abstract void decreaseSeatCount();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }
}
