import java.util.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Booking {
    String passengerName;
    int trainNo;
    Date date;
    int seats;
    String boardingPoint;
    String destinationPoint;

    Booking(Scanner scanner) {
        System.out.println("Enter name of passenger: ");
        passengerName = scanner.nextLine();

        // Validate train number input
        while (true) {
            System.out.println("Enter train no: ");
            if (scanner.hasNextInt()) {
                trainNo = scanner.nextInt();
                scanner.nextLine(); // consume the newline character left by nextInt()
                break;
            } else {
                System.out.println("Invalid input. Please enter a valid train number.");
                scanner.nextLine(); // Clear the invalid input
            }
        }

        System.out.println("Enter date (dd-MM-yyyy):");
        String dateInput = scanner.nextLine();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        try {
            date = dateFormat.parse(dateInput);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Validate seats input
        while (true) {
            System.out.println("Enter number of seats: ");
            if (scanner.hasNextInt()) {
                seats = scanner.nextInt();
                scanner.nextLine(); // consume the newline character left by nextInt()
                break;
            } else {
                System.out.println("Invalid input. Please enter a valid number of seats.");
                scanner.nextLine(); // Clear the invalid input
            }
        }

        System.out.println("Enter boarding point: ");
        boardingPoint = scanner.nextLine();

        System.out.println("Enter destination point: ");
        destinationPoint = scanner.nextLine();
    }

    public boolean isAvailable() throws SQLException {
        TrainDAO traindao = new TrainDAO();
        BookingDAO bookingdao = new BookingDAO();
        int capacity = traindao.getCapacity(trainNo);
        int booked = bookingdao.getBookedCount(trainNo, date);
        return booked + seats <= capacity;
    }
}
