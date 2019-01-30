package com.tcs.hack.repository;

import com.tcs.hack.model.Booking;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Integer>{

//    @Query("SELECT count(b) FROM booking b WHERE b.resource = :resource and b.bookingdate = :date and b.bookingslot = :slot")
//    public int findAlreadyBooked(@Param("resource") Resource resource, @Param("date") java.sql.Date date, @Param("slot") String slot);

    @Query("SELECT count(b) FROM Booking b WHERE b.resource = :resourceId and b.bookingDate = :date and b.bookingSlot = :slot")
    public int findAvailability(@Param("resourceId") int resourceId, @Param("date") java.sql.Date date, @Param("slot") String slot);

    @Query(value = "select b from Booking b where b.bookingId = :bookingId")
    Booking findOne(@Param("bookingId") int bookingId);
}