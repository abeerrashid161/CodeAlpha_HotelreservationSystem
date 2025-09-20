import java.io.*;
import java.util.*;

class Room {
    private int roomNumber;
    private String category; // Standard, Deluxe, Suite
    private boolean isAvailable;

    public Room(int roomNumber, String category) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.isAvailable = true;
    }

    public int getRoomNumber() { return roomNumber; }
    public String getCategory() { return category; }
    public boolean isAvailable() { return isAvailable; }

    public void book() { isAvailable = false; }
    public void cancel() { isAvailable = true; }

    @Override
    public String toString() {
        return "Room " + roomNumber + " (" + category + ") - " +
               (isAvailable ? "Available" : "Booked");
    }
}

class Reservation {
    private String guestName;
    private Room room;
    private double amountPaid;

    public Reservation(String guestName, Room room, double amountPaid) {
        this.guestName = guestName;
        this.room = room;
        this.amountPaid = amountPaid;
    }

    public String getGuestName() { return guestName; }
    public Room getRoom() { return room; }
    public double getAmountPaid() { return amountPaid; }

    @Override
    public String toString() {
        return "Reservation: " + guestName + " booked Room " + room.getRoomNumber() +
               " (" + room.getCategory() + ") | Paid: $" + amountPaid;
    }
}

class Hotel {
    private ArrayList<Room> rooms;
    private ArrayList<Reservation> reservations;
    private final String FILE_NAME = "reservations.txt";

    public Hotel() {
        rooms = new ArrayList<>();
        reservations = new ArrayList<>();
        loadRooms();
        loadReservations();
    }

    private void loadRooms() {
        // Initialize some rooms
        rooms.add(new Room(101, "Standard"));
        rooms.add(new Room(102, "Standard"));
        rooms.add(new Room(201, "Deluxe"));
        rooms.add(new Room(202, "Deluxe"));
        rooms.add(new Room(301, "Suite"));
    }

    public void searchRooms(String category) {
        System.out.println("\nAvailable " + category + " rooms:");
        for (Room room : rooms) {
            if (room.getCategory().equalsIgnoreCase(category) && room.isAvailable()) {
                System.out.println(room);
            }
        }
    }

    public void makeReservation(String guestName, String category) {
        for (Room room : rooms) {
            if (room.getCategory().equalsIgnoreCase(category) && room.isAvailable()) {
                double price = getPrice(category);

                System.out.println("Room found: " + room);
                System.out.println("Price: $" + price);
                System.out.print("Proceed with payment? (yes/no): ");
                Scanner sc = new Scanner(System.in);
                if (sc.nextLine().equalsIgnoreCase("yes")) {
                    room.book();
                    Reservation res = new Reservation(guestName, room, price);
                    reservations.add(res);
                    saveReservations();
                    System.out.println("✅ Booking successful!\n" + res);
                } else {
                    System.out.println("❌ Payment cancelled. Reservation not made.");
                }
                return;
            }
        }
        System.out.println("❌ No available " + category + " rooms.");
    }

    public void cancelReservation(String guestName) {
        Iterator<Reservation> it = reservations.iterator();
        while (it.hasNext()) {
            Reservation res = it.next();
            if (res.getGuestName().equalsIgnoreCase(guestName)) {
                res.getRoom().cancel();
                it.remove();
                saveReservations();
                System.out.println("✅ Reservation cancelled for " + guestName);
                return;
            }
        }
        System.out.println("❌ No reservation found for " + guestName);
    }

    public void viewReservations() {
        if (reservations.isEmpty()) {
            System.out.println("\nNo reservations yet.");
        } else {
            System.out.println("\n=== Current Reservations ===");
            for (Reservation res : reservations) {
                System.out.println(res);
            }
        }
    }

    private double getPrice(String category) {
        switch (category.toLowerCase()) {
            case "standard": return 100.0;
            case "deluxe": return 200.0;
            case "suite": return 350.0;
            default: return 0.0;
        }
    }

    private void saveReservations() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Reservation res : reservations) {
                pw.println(res.getGuestName() + "," + res.getRoom().getRoomNumber() +
                           "," + res.getRoom().getCategory() + "," + res.getAmountPaid());
            }
        } catch (IOException e) {
            System.out.println("Error saving reservations.");
        }
    }

    private void loadReservations() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String guestName = parts[0];
                int roomNum = Integer.parseInt(parts[1]);
                String category = parts[2];
                double amountPaid = Double.parseDouble(parts[3]);

                for (Room room : rooms) {
                    if (room.getRoomNumber() == roomNum) {
                        room.book();
                        reservations.add(new Reservation(guestName, room, amountPaid));
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading reservations.");
        }
    }
}

public class HotelReservationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hotel hotel = new Hotel();

        while (true) {
            System.out.println("\n===== Hotel Reservation System =====");
            System.out.println("1. Search Rooms");
            System.out.println("2. Make Reservation");
            System.out.println("3. Cancel Reservation");
            System.out.println("4. View Reservations");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // clear buffer

            switch (choice) {
                case 1:
                    System.out.print("Enter room category (Standard/Deluxe/Suite): ");
                    hotel.searchRooms(scanner.nextLine());
                    break;
                case 2:
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter room category (Standard/Deluxe/Suite): ");
                    hotel.makeReservation(name, scanner.nextLine());
                    break;
                case 3:
                    System.out.print("Enter your name to cancel reservation: ");
                    hotel.cancelReservation(scanner.nextLine());
                    break;
                case 4:
                    hotel.viewReservations();
                    break;
                case 5:
                    System.out.println("👋 Thank you for using Hotel Reservation System!");
                    return;
                default:
                    System.out.println("❌ Invalid choice, try again.");
            }
        }
    }
}