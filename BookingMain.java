package com.hotelbooking.main;

import com.hotelbooking.vo.Booking;
import com.hotelbooking.bo.BookingBO;
import com.hotelbooking.exception.BookingException;
import com.hotelbooking.response.ResponseObject;
import com.hotelbooking.service.BookingService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class BookingMain {

    // ✅ Correct logger initialization
    static Logger logger = Logger.getLogger(BookingMain.class);

    static Scanner sc = new Scanner(System.in);
    static BookingService service = new BookingService();
    static BookingBO bo = new BookingBO();

    public static void main(String[] args) {
        // ✅ Configure log4j using relative path (since it's inside src/)
        PropertyConfigurator.configure("log4j.properties");

        logger.info("Hotel Booking Application Started");

        int choice;
        do {
            System.out.println("\n==== Hotel Booking Menu ====");
            System.out.println("1. Add Booking");
            System.out.println("2. View All Bookings");
            System.out.println("3. Search Booking by ID");
            System.out.println("4. Update Booking");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = Integer.parseInt(sc.nextLine());

            try {
                switch (choice) {
                    case 1:
                        logger.info("Option 1 selected - Add Booking");
                        addBooking();
                        break;
                    case 2:
                        logger.info("Option 2 selected - View All Bookings");
                        viewAll();
                        break;
                    case 3:
                        logger.info("Option 3 selected - Search Booking by ID");
                        searchById();
                        break;
                    case 4:
                        logger.info("Option 4 selected - Update Booking");
                        updateBooking();
                        break;
                    case 5:
                        logger.info("Option 5 selected - Exit");
                        System.out.println("Exiting...");
                        break;
                    default:
                        logger.warn("Invalid choice entered: " + choice);
                        System.out.println("Invalid choice.");
                }
            } catch (BookingException e) {
                logger.error("BookingException occurred: " + e.getMessage(), e);
                System.out.println(new ResponseObject("FAILURE", e.getMessage()));
            } catch (Exception e) {
                logger.error("Unexpected Exception: " + e.getMessage(), e);
                System.out.println(new ResponseObject("FAILURE", "An unexpected error occurred."));
            }
        } while (choice != 5);

        logger.info("Hotel Booking Application Ended");
    }

    private static void addBooking() throws BookingException {
        Booking b = readBookingDetails(false);
        bo.validate(b);
        boolean added = service.addBooking(b);
        if (added) {
            logger.info("Booking added successfully for: " + b.getCustomerName());
            System.out.println(new ResponseObject("SUCCESS", "Booking added successfully."));
        }
    }

    private static void viewAll() throws BookingException {
        List<Booking> bookings = service.findAllBookings();
        if (bookings.isEmpty()) {
            logger.info("No bookings found.");
            System.out.println("No bookings found.");
        } else {
            logger.info("Displaying all bookings.");
            bookings.forEach(System.out::println);
        }
    }

    private static void searchById() throws BookingException {
        System.out.print("Enter booking ID: ");
        int id = Integer.parseInt(sc.nextLine());
        Booking b = service.findBookingById(id);
        logger.info("Booking found: " + b);
        System.out.println(b);
    }

    private static void updateBooking() throws BookingException {
        System.out.print("Enter booking ID to update: ");
        int id = Integer.parseInt(sc.nextLine());
        Booking b = readBookingDetails(true);
        b.setBookingId(id);
        bo.validate(b);
        boolean updated = service.updateBooking(b);
        if (updated) {
            logger.info("Booking updated for ID: " + id);
            System.out.println(new ResponseObject("SUCCESS", "Booking updated."));
        }
    }

    private static Booking readBookingDetails(boolean isUpdate) {
        Booking b = new Booking();

        System.out.print("Customer Name: ");
        b.setCustomerName(sc.nextLine());

        System.out.print("Room Type (AC/Non-AC/Deluxe): ");
        b.setRoomType(sc.nextLine());

        System.out.print("Check-in Date (yyyy-mm-dd): ");
        b.setCheckInDate(LocalDate.parse(sc.nextLine()));

        System.out.print("Check-out Date (yyyy-mm-dd): ");
        b.setCheckOutDate(LocalDate.parse(sc.nextLine()));

        System.out.print("Number of Guests: ");
        b.setNumberOfGuests(Integer.parseInt(sc.nextLine()));

        return b;
    }
}
