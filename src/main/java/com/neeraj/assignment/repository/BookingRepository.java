package com.neeraj.assignment.repository;

import com.neeraj.assignment.model.Booking;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Integer>{

    @Query("SELECT count(b) FROM booking b WHERE b.resourceid = :resourceId and b.bookingdate = :date and b.bookingslot = :slot")
    public int findbAvailability(@Param("resourceId") int resourceId, @Param("date") java.sql.Date date, @Param("slot") String slot);
}