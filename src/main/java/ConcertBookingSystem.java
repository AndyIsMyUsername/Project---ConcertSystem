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
        while (true) {
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
            } else if (choice == 2) {
                currentUser = new Customer(id, name);
                customerMenu();
            } else {
                System.out.println("Invalid number try again.");
            }

            try {
                FileManager.writeConcertsToFile(concerts, CONCERTS_FILE);
            } catch (Exception e) {
                System.out.println("Error saving concerts data.");
            }
        }
    }

    /**
     * menu for admin
     */
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

    /**
     * add a concert
     * @param admin admin have access to it
     */
    private static void addConcert(Admin admin) {
        System.out.println("Enter concert name : ");
        String name = scanner.nextLine();
        System.out.println("Enter date (yyyy-mm-dd) : ");
        LocalDate date = LocalDate.parse(scanner.nextLine());
        System.out.println("Enter venue : ");
        String venue = scanner.nextLine();
        System.out.println("Number of seats : ");
        int seats = scanner.nextInt();
        scanner.nextLine();

        Concert concert = new Concert(name, date ,venue, seats);
        admin.addConcert(concerts, concert);
        System.out.println("Concert Added.");
    }

    /**
     * remove concert
     * @param admin can remove concert from system
     */
    private static void removeConcert(Admin admin) {
        System.out.println("Available Concerts : ");
        concerts.forEach(c -> System.out.println((concerts.indexOf(c) + 1) + ". " + c.getName()));

        System.out.println("Select one that you want to remove : ");
        int idx = scanner.nextInt() -1;
        scanner.nextLine();

        if (idx >= 0 && idx < concerts.size()) {
            Concert concert = concerts.get(idx);
            admin.removeConcert(concerts, concert);
            System.out.println("Concert removed successfully.");
        } else {
            System.out.println("Invalid please try again.");
        }
    }

    /**
     * customer menu
     */
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
                            .filter(c -> c.availableSeats() > 0)
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

    /**
     * book a concert
     * @param customer can book a concert created by admin
     */
    private static void bookConcert(Customer customer) {
        List<Concert> availableConcerts = concerts.stream()
                .filter(c -> c.availableSeats() > 0)
                .collect(Collectors.toList());

        if (availableConcerts.isEmpty()) {
            System.out.println("No concerts available for booking.");
            return;
        }

        System.out.println("Available concerts: ");
        for (int i = 0; i < availableConcerts.size(); i++ ) {
            Concert c = availableConcerts.get(i);
            System.out.println((i+1) + ". " + c.getName() + " (" + c.availableSeats() + " seats available)");
        }

        System.out.println("Select concert to book : ");
        int idx = scanner.nextInt() - 1;
        scanner.nextLine();

        if (idx >= 0 && idx < availableConcerts.size()) {
            Concert selected = availableConcerts.get(idx);
            customer.bookConcerts(selected);
        } else {
            System.out.println("Invalid selection");
        }
    }

    /**
     * Customer can cancel booking
     * @param customer removes booking
     */
    private static void cancelBooking(Customer customer) {
        if (customer.getBookedConcerts().isEmpty()) {
            System.out.println("You have no bookings to cancel. ");
            return;
        }

        System.out.println("Your bookings : ");
        List<Concert> bookings = customer.getBookedConcerts();
        for (int i = 0; i < bookings.size(); i++) {
            System.out.println((i + 1) + ". " + bookings.get(i).getName());
        }

        System.out.println("Select booking to cancel");
        int idx = scanner.nextInt() - 1;
        scanner.nextLine();

        if (idx >= 0 && idx < bookings.size()) {
            customer.cancelBooking(bookings.get(idx));
        } else {
            System.out.println("Invalid selection");
        }
    }
}
