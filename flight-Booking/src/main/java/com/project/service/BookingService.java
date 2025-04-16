package com.project.service;

import com.project.model.Booking;

import java.util.List;

public interface BookingService {
    Booking createBooking(Booking booking);
    Booking getBookingById(Long id) throws Exception;
    List<Booking> getBookingsByUser(Long userId);
    void cancleBooking(Long id);
}
