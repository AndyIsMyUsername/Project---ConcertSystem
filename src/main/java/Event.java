import java.time.LocalDate;

public abstract class Event {
    protected String name;
    protected LocalDate date;
    protected String venue;
    protected EventStatus status;

    public Event(String name, LocalDate date, String venue) {
        this.name = name;
        this.date = date;
        this.venue = venue;
        this.status = EventStatus.ACTIVE;
    }

    public enum EventStatus {
        ACTIVE, CANCELLED, SOLD_OUT, POSTPONED
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

    public boolean isCancelled() {
        return status == EventStatus.CANCELLED;
    }


    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", date=" + date +
                ", venue='" + venue + '\'' +
                ", status=" + status +
                '}';
    }

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

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
