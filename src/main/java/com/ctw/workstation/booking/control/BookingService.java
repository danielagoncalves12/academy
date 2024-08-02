package com.ctw.workstation.booking.control;

import com.ctw.workstation.booking.entity.Booking;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class BookingService {

    public Booking create(Booking booking) {
        Booking.persist(booking);
        return booking;
    }

    public Booking getById(Long id) {
        return Booking.findById(id);
    }

    public boolean delete(Long id) {
        return Booking.deleteById(id);
    }

    public Booking update(Long id, Booking booking) {

        Booking bookingFound = getById(id);

        if (bookingFound != null) {
            bookingFound.setBookFrom(booking.getBookFrom());
            bookingFound.setBookTo(booking.getBookTo());
            bookingFound.setModifiedAt(LocalDateTime.now());
            Booking.persist(bookingFound);
        }

        return bookingFound;
    }

    public List<Booking> getBookings() {
        return Booking.listAll();
    }
}



