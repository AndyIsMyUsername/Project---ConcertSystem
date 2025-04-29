package ConcertSystem;

public abstract class User {
    private String id;
    private String name;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * abstract method allowing for both admin and customer to view events
     */
    public abstract void viewEvents();
}
