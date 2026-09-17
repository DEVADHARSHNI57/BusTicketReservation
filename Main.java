import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main.java
 * Entry point of the Bus Ticket Reservation System.
 * Holds the collections of Bus and Booking objects in memory (no database)
 * and drives a simple console menu.
 *
 * OOP concepts demonstrated:
 *  - Encapsulation: Bus & Booking hide their state behind getters/methods.
 *  - Association:   Booking holds a reference to a Bus object.
 *  - Abstraction:   Main only calls high-level methods like reserveSeats(),
 *                   cancel(), without knowing their internal implementation.
 */
public class Main {

    private static final List<Bus> buses = new ArrayList<>();
    private static final List<Booking> bookings = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        seedBuses();

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    viewBuses();
                    break;
                case 2:
                    bookTicket();
                    break;
                case 3:
                    cancelTicket();
                    break;
                case 4:
                    viewBookings();
                    break;
                case 5:
                    running = false;
                    System.out.println("Thank you for using the Bus Ticket Reservation System!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.\n");
            }
        }
        scanner.close();
    }

    // ---------- Setup ----------

    private static void seedBuses() {
        buses.add(new Bus("B101", "Express Line", "Chennai", "Bangalore", 550.0, 40));
        buses.add(new Bus("B102", "City Cruiser", "Chennai", "Madurai", 450.0, 35));
        buses.add(new Bus("B103", "Night Rider", "Chennai", "Coimbatore", 650.0, 30));
    }

    // ---------- Menu actions ----------

    private static void printMenu() {
        System.out.println("===== Bus Ticket Reservation System =====");
        System.out.println("1. View Available Buses");
        System.out.println("2. Book a Ticket");
        System.out.println("3. Cancel a Ticket");
        System.out.println("4. View All Bookings");
        System.out.println("5. Exit");
    }

    private static void viewBuses() {
        System.out.println("\n--- Available Buses ---");
        for (Bus bus : buses) {
            System.out.println(bus);
        }
        System.out.println();
    }

    private static void bookTicket() {
        viewBuses();
        System.out.print("Enter Bus ID to book: ");
        String busId = scanner.nextLine().trim();

        Bus selectedBus = findBusById(busId);
        if (selectedBus == null) {
            System.out.println("Bus not found.\n");
            return;
        }

        System.out.print("Enter passenger name: ");
        String passengerName = scanner.nextLine().trim();

        int seats = readInt("Enter number of seats to book: ");

        boolean success = selectedBus.reserveSeats(seats);
        if (success) {
            Booking booking = new Booking(passengerName, selectedBus, seats);
            bookings.add(booking);
            System.out.println("Booking successful!");
            System.out.println(booking);
        } else {
            System.out.println("Booking failed. Not enough seats available.");
        }
        System.out.println();
    }

    private static void cancelTicket() {
        int bookingId = readInt("Enter Booking ID to cancel: ");
        Booking booking = findBookingById(bookingId);

        if (booking == null) {
            System.out.println("Booking not found.\n");
            return;
        }

        boolean cancelled = booking.cancel();
        if (cancelled) {
            System.out.println("Booking #" + bookingId + " cancelled successfully.\n");
        } else {
            System.out.println("Booking #" + bookingId + " was already cancelled.\n");
        }
    }

    private static void viewBookings() {
        System.out.println("\n--- All Bookings ---");
        if (bookings.isEmpty()) {
            System.out.println("No bookings yet.");
        } else {
            for (Booking booking : bookings) {
                System.out.println(booking);
            }
        }
        System.out.println();
    }

    // ---------- Helpers ----------

    private static Bus findBusById(String busId) {
        for (Bus bus : buses) {
            if (bus.getBusId().equalsIgnoreCase(busId)) {
                return bus;
            }
        }
        return null;
    }

    private static Booking findBookingById(int bookingId) {
        for (Booking booking : bookings) {
            if (booking.getBookingId() == bookingId) {
                return booking;
            }
        }
        return null;
    }

    private static int readInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // consume leftover newline
        return value;
    }
}
