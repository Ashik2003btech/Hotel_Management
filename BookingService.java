package com.hotelbooking.service;

import com.hotelbooking.dao.BookingDAOImpl;
import com.hotelbooking.dao.IBookingDAO;
import com.hotelbooking.exception.BookingException;
import com.hotelbooking.vo.Booking;
import java.util.List;

public class BookingService {
    private final IBookingDAO dao = new BookingDAOImpl();

    public boolean addBooking(Booking booking) throws BookingException {
        return dao.addBooking(booking);
    }

    public Booking findBookingById(int id) throws BookingException {
        return dao.findBookingById(id);
    }

    public List<Booking> findAllBookings() throws BookingException {
        return dao.findAllBookings();
    }

    public boolean updateBooking(Booking booking) throws BookingException {
        return dao.updateBooking(booking);
    }
}
