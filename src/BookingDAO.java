import java.util.Date;
import java.sql.*;

public class BookingDAO {

	public int getBookedCount(int trainNo, Date date) throws SQLException {
	    System.out.println("Checking booked count for trainNo: " + trainNo + "\n on date: " + date);
	    String query = "SELECT SUM(seats) FROM booking WHERE train_no=? AND travel_date=?";
	    Connection con = DbConnection.getConnection();
	    PreparedStatement pst = con.prepareStatement(query);
	    java.sql.Date sqldate = new java.sql.Date(date.getTime());
	    pst.setInt(1, trainNo);
	    pst.setDate(2, sqldate);
	    ResultSet rs = pst.executeQuery();
	    
	    int bookedSeats = 0;
	    if (rs.next()) {
	        bookedSeats = rs.getInt(1);
	    } else {
	        System.out.println("No records found.");
	    }
	    rs.close();
	    return bookedSeats;
	}

    public void addBooking(Booking booking) throws SQLException {
        String query = "INSERT INTO booking (passenger_name, train_no, travel_date, seats, boarding_point, destination_point) VALUES (?, ?, ?, ?, ?, ?)";
        Connection con = DbConnection.getConnection();
        java.sql.Date sqldate = new java.sql.Date(booking.date.getTime());
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, booking.passengerName);
        pst.setInt(2, booking.trainNo);
        pst.setDate(3, sqldate);
        pst.setInt(4, booking.seats);
        pst.setString(5, booking.boardingPoint);
        pst.setString(6, booking.destinationPoint);
        
        pst.executeUpdate();
    }
}
