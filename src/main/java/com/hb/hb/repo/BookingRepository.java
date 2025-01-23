package com.hb.hb.repo;

import com.hb.hb.entity.Booking;
import com.hb.hb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookingRepository  extends JpaRepository<Booking,Long> {

    Optional<Booking> findByBookingConfirmationCode(String confirmationCode);
}
