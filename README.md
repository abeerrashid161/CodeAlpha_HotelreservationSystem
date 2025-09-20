🏨 Hotel Reservation System

A simple Java console-based Hotel Reservation System that allows users to:

Search available rooms

Make reservations (with simulated payment confirmation)

Cancel reservations

View all current reservations

The system also saves reservations to a file (reservations.txt) so that bookings persist even after the program is restarted.

📌 Features

Room Categories: Standard, Deluxe, Suite

Reservation Management:

Make new reservations

Cancel reservations by guest name

View all current reservations

Persistence: Reservations are stored in reservations.txt

Dynamic Pricing:

Standard → $100

Deluxe → $200

Suite → $350

Input validation and graceful error handling

📂 Project Structure
HotelReservationSystem.java   # Main entry point
Room.java                     # Handles room details and availability
Reservation.java              # Stores guest and booking details
Hotel.java                    # Core logic (reservations, file handling, pricing)
reservations.txt              # Saved reservation data (auto-generated)


(In your code, all classes are inside one file, but they can also be separated into their own files for better organization.)

⚙️ Requirements

Java 8 or later

Command line or any IDE (IntelliJ, Eclipse, VS Code, NetBeans)

🚀 How to Run

Compile the program:

javac HotelReservationSystem.java


Run the program:

java HotelReservationSystem

🖥️ Example Usage

Menu:

===== Hotel Reservation System =====
1. Search Rooms
2. Make Reservation
3. Cancel Reservation
4. View Reservations
5. Exit
Choose an option:

✅ Sample Session
===== Hotel Reservation System =====
1. Search Rooms
2. Make Reservation
3. Cancel Reservation
4. View Reservations
5. Exit
Choose an option: 1
Enter room category (Standard/Deluxe/Suite): Deluxe

Available Deluxe rooms:
Room 201 (Deluxe) - Available
Room 202 (Deluxe) - Available

Choose an option: 2
Enter your name: Alice
Enter room category (Standard/Deluxe/Suite): Deluxe
Room found: Room 201 (Deluxe) - Available
Price: $200.0
Proceed with payment? (yes/no): yes
✅ Booking successful!
Reservation: Alice booked Room 201 (Deluxe) | Paid: $200.0

📊 Data Persistence

All reservations are automatically saved in reservations.txt.

On program startup, reservations are loaded back into the system.

📚 Concepts Demonstrated

Object-Oriented Programming (OOP) in Java

File handling (FileReader, FileWriter, BufferedReader, PrintWriter)

Collections (ArrayList)

Encapsulation (private fields with getters/setters)

Control flow (switch, loops, input validation)

🤝 Possible Improvements

Add dates for reservations (check-in, check-out).

Support multiple hotels or branches.

Add authentication (admin vs guest).

GUI version using JavaFX or Swing.
