/**
 * Booking.java
 * Represents a single ticket booking made by a passenger.
 * Demonstrates ENCAPSULATION and ASSOCIATION (a Booking "has-a" Bus).
 */
public class Booking {
    // Static counter shared across all instances -> generates unique booking IDs
    private static int bookingCounter = 1000;

    private final int bookingId;
    private final String passengerName;
    private final Bus bus;
    private final int seatsBooked;
    private final double totalFare;
    private boolean cancelled;

    public Booking(String passengerName, Bus bus, int seatsBooked) {
        this.bookingId = ++bookingCounter;
        this.passengerName = passengerName;
        this.bus = bus;
        this.seatsBooked = seatsBooked;
        this.totalFare = bus.getFare() * seatsBooked;
        this.cancelled = false;
    }

    public int getBookingId() {
        return bookingId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public Bus getBus() {
        return bus;
    }

    public int getSeatsBooked() {
        return seatsBooked;
    }

    public double getTotalFare() {
        return totalFare;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    /**
     * Cancels this booking: releases the seats back to the bus
     * and marks the booking as cancelled. Returns false if already cancelled.
     */
    public boolean cancel() {
        if (cancelled) {
            return false;
        }
        bus.releaseSeats(seatsBooked);
        cancelled = true;
        return true;
    }

    @Override
    public String toString() {
        String status = cancelled ? "CANCELLED" : "CONFIRMED";
        return String.format(
            "Booking #%d | Passenger: %-12s | Bus: %s (%s -> %s) | Seats: %d | Total Fare: %.2f | Status: %s",
            bookingId, passengerName, bus.getBusName(), bus.getSource(), bus.getDestination(),
            seatsBooked, totalFare, status
        );
    }
}
