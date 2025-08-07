package com.hotelbooking.dao;

import java.util.List;

import com.hotelbooking.vo.Booking;
import com.hotelbooking.exception.BookingException;

public interface IBookingDAO {
    boolean addBooking(Booking booking) throws BookingException;
    Booking findBookingById(int bookingId) throws BookingException;
    List<Booking> findAllBookings() throws BookingException;
    boolean updateBooking(Booking booking) throws BookingException;
}
