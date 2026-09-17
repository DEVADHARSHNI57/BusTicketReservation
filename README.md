# Bus Ticket Reservation System (Java OOP)

A simple console-based Bus Ticket Reservation System written in core Java,
built using Object-Oriented Programming principles. No database is used —
all data (buses and bookings) is stored in memory using `ArrayList`.

## Project Structure

```
bus-ticket-reservation-system/
├── README.md
└── src/
    ├── Bus.java       # Bus entity: id, name, route, fare, seat inventory
    ├── Booking.java   # Booking entity: links a passenger to a Bus
    └── Main.java       # Console menu / program entry point
```

## OOP Concepts Used

| Concept        | Where it appears                                                      |
|----------------|------------------------------------------------------------------------|
| Encapsulation  | Private fields in `Bus` and `Booking`, accessed only via getters/methods |
| Abstraction    | `Main` calls `reserveSeats()`, `cancel()` without knowing internal logic |
| Association    | `Booking` holds a reference to a `Bus` object (has-a relationship)     |
| Static members | `Booking.bookingCounter` generates unique IDs across all instances      |

## Features

- View all available buses with live seat availability
- Book a ticket (validates seat availability before confirming)
- Cancel a ticket (releases seats back to the bus)
- View all bookings with their status (CONFIRMED / CANCELLED)

## How to Compile & Run

```bash
cd src
javac *.java
java Main
```

## Sample Buses (pre-loaded)

| Bus ID | Name          | Route                  | Fare  | Seats |
|--------|---------------|-------------------------|-------|-------|
| B101   | Express Line  | Chennai → Bangalore     | 550.0 | 40    |
| B102   | City Cruiser  | Chennai → Madurai       | 450.0 | 35    |
| B103   | Night Rider   | Chennai → Coimbatore    | 650.0 | 30    |

## Notes

This is an in-memory demo (no persistence/database) intended to
demonstrate core Java OOP concepts in a clean, minimal way. It can be
extended with file/DB persistence, exception handling for edge cases,
or a GUI layer if needed.
