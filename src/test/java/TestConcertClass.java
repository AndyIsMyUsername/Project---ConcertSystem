import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConcertBookingSystemTest {
    private Admin admin;
    private Customer customer;
    private List<Concert> concerts;
    private Concert concert1;
    private Concert concert2;

    @BeforeEach
    void setUp() {
        admin = new Admin("admin1", "Admin User");
        customer = new Customer("cust1", "Customer User");
        concerts = new ArrayList<>();

        concert1 = new Concert("Concert1", LocalDate.of(2023, 12, 15), "Main Arena", 100);
        concert2 = new Concert("Concert2", LocalDate.of(2023, 11, 20), "City Hall", 50);
    }

    // Admin class tests
    @Test
    void testAdminAddConcert() {
        admin.addConcert(concerts, concert1);
        assertEquals(1, concerts.size());
        assertEquals("Concert1", concerts.get(0).getName());
    }

    @Test
    void testAdminRemoveConcert() {
        concerts.add(concert1);
        concerts.add(concert2);
        admin.removeConcert(concerts, concert1);
        assertEquals(1, concerts.size());
        assertEquals("Concert2", concerts.get(0).getName());
    }

    @Test
    void testAdminViewEvents() {
        concerts.add(concert1);
        concerts.add(concert2);
        admin.viewEvents(concerts);
    }

    @Test
    void testAdminViewAllBookings() {
        concert1.bookTickets();
        concert2.bookTickets();
        concerts.add(concert1);
        concerts.add(concert2);
        admin.viewAllBookings(concerts);
    }

    // Customer class tests
    @Test
    void testCustomerBookConcerts() {
        customer.bookConcerts(concert1);
        assertEquals(1, concert1.getBooked());
        assertEquals(1, customer.getBookedConcerts().size());
    }

    @Test
    void testCustomerCancelBooking() {
        concert1.bookTickets();
        customer.getBookedConcerts().add(concert1);
        customer.cancelBooking(concert1);
        assertEquals(0, concert1.getBooked());
        assertTrue(customer.getBookedConcerts().isEmpty());
    }

    @Test
    void testCustomerViewEvents() {
        concerts.add(concert1);
        concerts.add(concert2);
        customer.viewEvents(concerts);
    }

    @Test
    void testCustomerViewMyBookings() {
        customer.getBookedConcerts().add(concert1);
        customer.viewMyBookings();
    }

    // Concert class tests
    @Test
    void testConcertBookTickets() {
        concert1.bookTickets();
        assertEquals(1, concert1.getBooked());
        assertEquals(99, concert1.availableSeats());
    }

    @Test
    void testConcertBookTicketsThrowsWhenFull() {
        for (int i = 0; i < 100; i++) {
            concert1.bookTickets();
        }
        assertThrows(IllegalStateException.class, () -> concert1.bookTickets());
    }

    @Test
    void testConcertAvailableSeats() {
        assertEquals(100, concert1.availableSeats());
        concert1.bookTickets();
        assertEquals(99, concert1.availableSeats());
    }

    @Test
    void testConcertDecreaseSeatCount() {
        concert1.decreaseSeatCount();
        assertEquals(1, concert1.getBooked());
    }

    @Test
    void testConcertCompareTo() {
        assertTrue(concert2.compareTo(concert1) < 0);
    }

    // PopularityComparator tests
    @Test
    void testPopularityComparator() {
        concert1.bookTickets();
        concert1.bookTickets();
        concert2.bookTickets();

        PopularityComparator comparator = new PopularityComparator();
        assertTrue(comparator.compare(concert1, concert2) < 0);
    }

    // User class tests

    @Test
    void testUserViewEvents() {
        concerts.add(concert1);
        admin.viewEvents(concerts);
    }

    // FileManager tests
    @Test
    void testFileManagerReadWriteConcerts() {
        List<Concert> testConcerts = new ArrayList<>();
        testConcerts.add(concert1);
        testConcerts.add(concert2);

        String testFile = "test_concerts.txt";
        FileManager.writeConcertsToFile(testConcerts, testFile);

        List<Concert> loadedConcerts = FileManager.readConcerts(testFile);
        assertEquals(2, loadedConcerts.size());
        assertEquals("Concert1", loadedConcerts.get(0).getName());
        assertEquals("Concert2", loadedConcerts.get(1).getName());
    }
}
