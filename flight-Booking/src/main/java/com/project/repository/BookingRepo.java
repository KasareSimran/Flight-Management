package com.project.repository;

import com.project.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookingRepo extends JpaRepository<Booking,Long> {
    List<Booking> findByUserId(Long userId);
}
