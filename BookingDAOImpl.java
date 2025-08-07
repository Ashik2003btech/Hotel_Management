package com.hotelbooking.dao;

import com.hotelbooking.vo.Booking;
import com.hotelbooking.exception.BookingException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingDAOImpl implements IBookingDAO {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/hotel_db";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "Ashik@2003"; // Change to your password

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }

    @Override
    public boolean addBooking(Booking booking) throws BookingException {
        String sql = "INSERT INTO bookings (customer_name, room_type, check_in_date, check_out_date, number_of_guests) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, booking.getCustomerName());
            ps.setString(2, booking.getRoomType());
            ps.setDate(3, Date.valueOf(booking.getCheckInDate()));
            ps.setDate(4, Date.valueOf(booking.getCheckOutDate()));
            ps.setInt(5, booking.getNumberOfGuests());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new BookingException("Error adding booking: " + e.getMessage());
        }
    }

    @Override
    public Booking findBookingById(int bookingId) throws BookingException {
        String sql = "SELECT * FROM bookings WHERE booking_id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, bookingId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Booking(
                        rs.getInt("booking_id"),
                        rs.getString("customer_name"),
                        rs.getString("room_type"),
                        rs.getDate("check_in_date").toLocalDate(),
                        rs.getDate("check_out_date").toLocalDate(),
                        rs.getInt("number_of_guests")
                );
            } else {
                throw new BookingException("Booking ID not found.");
            }
        } catch (SQLException e) {
            throw new BookingException("Error fetching booking: " + e.getMessage());
        }
    }

    @Override
    public List<Booking> findAllBookings() throws BookingException {
        List<Booking> list = new ArrayList<>();
        String sql = "SELECT * FROM bookings";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Booking b = new Booking(
                        rs.getInt("booking_id"),
                        rs.getString("customer_name"),
                        rs.getString("room_type"),
                        rs.getDate("check_in_date").toLocalDate(),
                        rs.getDate("check_out_date").toLocalDate(),
                        rs.getInt("number_of_guests")
                );
                list.add(b);
            }
        } catch (SQLException e) {
            throw new BookingException("Error retrieving all bookings: " + e.getMessage());
        }
        return list;
    }

    @Override
    public boolean updateBooking(Booking booking) throws BookingException {
        String sql = "UPDATE bookings SET customer_name = ?, room_type = ?, check_in_date = ?, check_out_date = ?, number_of_guests = ? WHERE booking_id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, booking.getCustomerName());
            ps.setString(2, booking.getRoomType());
            ps.setDate(3, Date.valueOf(booking.getCheckInDate()));
            ps.setDate(4, Date.valueOf(booking.getCheckOutDate()));
            ps.setInt(5, booking.getNumberOfGuests());
            ps.setInt(6, booking.getBookingId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new BookingException("Error updating booking: " + e.getMessage());
        }
    }
}
