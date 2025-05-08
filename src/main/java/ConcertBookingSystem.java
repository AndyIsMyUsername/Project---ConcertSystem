import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConcertBookingSystem {

    private static final String CONCERTS_FILE = "concerts.txt";
    private static Scanner scanner = new Scanner(System.in);
    private static List<Concert> concerts;
    private static User currentUser;

    public static void main(String[] args) {

        try {
            concerts = FileManager.readConcerts(CONCERTS_FILE);
        } catch (RuntimeException e) {
            System.out.println("Error loading concerts. Starting with empty list.");
            concerts = new ArrayList<>();
        }

        System.out.println("Booking System");
        System.out.println("1. Admin Login");
        System.out.println("2. Customer Login");
        System.out.print("Select role: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter your ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        if (choice == 1) {
            currentUser = new Admin(id, name);
            adminMenu();
        } else {
            currentUser = new Customer(id, name);
            customerMenu();
        }

        try {
            FileManager.writeConcertsToFile(concerts, CONCERTS_FILE);
        } catch (Exception e) {
            System.out.println("Error saving concerts data.");
        }
    }

    private static void adminMenu() {
        Admin admin = (Admin) currentUser;
        while (true) {
            System.out.println("\nAdmin Menu");
            System.out.println("1. Add Concert");
            System.out.println("2. Remove Concert");
            System.out.println("3. View All Bookings");
            System.out.println("4. View All Concerts");
            System.out.println("5. Exit");
            System.out.print("Select option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addConcert(admin);
                    break;
                case 2:
                    removeConcert(admin);
                    break;
                case 3:
                    admin.viewAllBookings(concerts);
                    break;
                case 4:
                    admin.viewEvents(concerts);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void addConcert(Admin admin) {

    }

    private static void removeConcert(Admin admin) {

    }

    private static void customerMenu() {
        Customer customer = (Customer) currentUser;
        while (true) {
            System.out.println("Customer Menu");
            System.out.println("1. View Available Concerts");
            System.out.println("2. Book Concert");
            System.out.println("3. Cancel Booking");
            System.out.println("4. View My Bookings");
            System.out.println("5. Exit");
            System.out.print("Select option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    List<Concert> availableConcerts = concerts.stream()
                            .filter(c -> c.availableSeats() > 0 && !c.isCancelled())
                            .collect(Collectors.toList());
                    customer.viewEvents(availableConcerts);
                    break;
                case 2:
                    bookConcert(customer);
                    break;
                case 3:
                    cancelBooking(customer);
                    break;
                case 4:
                    customer.viewMyBookings();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void bookConcert(Customer customer) {

    }

    private static void cancelBooking(Customer customer) {

    }
}
