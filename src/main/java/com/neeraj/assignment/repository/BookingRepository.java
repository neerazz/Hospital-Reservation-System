package com.neeraj.assignment.repository;

import com.neeraj.assignment.model.Booking;
import org.springframework.data.repository.CrudRepository;

public interface BookingRepository extends CrudRepository<Booking, Integer>{
//    public int findbAvailability(int resourceId, java.sql.Date date, String slot);
}
