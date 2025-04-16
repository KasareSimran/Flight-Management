package com.project.service;

import com.project.model.Booking;
import com.project.repository.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class BookingServiceImpl implements BookingService{


    @Autowired
    private BookingRepo bookingRepo;



    @Override
    public Booking createBooking(Booking booking) {
        return bookingRepo.save(booking);

    }

    @Override
    public Booking getBookingById(Long id) throws Exception {
        return bookingRepo.findById(id)
                .orElseThrow(() -> new Exception("Booking not found"));
    }

    @Override
    public List<Booking> getBookingsByUser(Long userId) {
        return bookingRepo.findByUserId(userId);
    }

    @Override
    public void cancleBooking(Long id) {
        bookingRepo.deleteById(id);
    }
}
