package com.hotelbooking.bo;

import com.hotelbooking.vo.Booking;
import com.hotelbooking.exception.BookingException;

public class BookingBO {

    public void validate(Booking booking) throws BookingException {
        if (booking.getCustomerName() == null || booking.getCustomerName().trim().isEmpty()) {
            throw new BookingException("Customer name is required.");
        }

        if (booking.getRoomType() == null || booking.getRoomType().trim().isEmpty()) {
            throw new BookingException("Room type is required.");
        }

        if (booking.getCheckInDate() == null || booking.getCheckOutDate() == null) {
            throw new BookingException("Check-in and check-out dates are required.");
        }

        if (booking.getCheckOutDate().isBefore(booking.getCheckInDate())) {
            throw new BookingException("Check-out date cannot be before check-in date.");
        }

        if (booking.getNumberOfGuests() <= 0) {
            throw new BookingException("Number of guests must be positive.");
        }
    }
}
