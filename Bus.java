/**
 * Bus.java
 * Represents a Bus entity in the reservation system.
 * Demonstrates ENCAPSULATION (private fields + public getters/setters).
 */
public class Bus {
    private String busId;
    private String busName;
    private String source;
    private String destination;
    private double fare;
    private int totalSeats;
    private int availableSeats;

    public Bus(String busId, String busName, String source, String destination,
                double fare, int totalSeats) {
        this.busId = busId;
        this.busName = busName;
        this.source = source;
        this.destination = destination;
        this.fare = fare;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats; // all seats free initially
    }

    // ----- Getters -----
    public String getBusId() {
        return busId;
    }

    public String getBusName() {
        return busName;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public double getFare() {
        return fare;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    // ----- Business logic -----

    /**
     * Reserves the given number of seats if available.
     * @return true if reservation succeeded, false if not enough seats.
     */
    public boolean reserveSeats(int numberOfSeats) {
        if (numberOfSeats <= 0) {
            return false;
        }
        if (availableSeats >= numberOfSeats) {
            availableSeats -= numberOfSeats;
            return true;
        }
        return false;
    }

    /**
     * Releases previously booked seats back to the pool (used on cancellation).
     */
    public void releaseSeats(int numberOfSeats) {
        availableSeats = Math.min(totalSeats, availableSeats + numberOfSeats);
    }

    @Override
    public String toString() {
        return String.format(
            "%-8s | %-15s | %-10s -> %-10s | Fare: %-8.2f | Seats Available: %d/%d",
            busId, busName, source, destination, fare, availableSeats, totalSeats
        );
    }
}
