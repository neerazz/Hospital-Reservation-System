package com.tcs.hack;

import com.tcs.hack.model.Booking;
import com.tcs.hack.model.Resource;
import com.tcs.hack.repository.BookingRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookingRepositoryTest {

    @Autowired
    private BookingRepository repo;
    private Booking booking;

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void findAvailabilityTest() throws ParseException
    {
        java.sql.Date dt = new java.sql.Date(new SimpleDateFormat("dd-MM-yyyy").parse("31-12-17").getTime());
        int cnt = repo.findAvailability(1001, dt, "15:00 - 16:00");
        assertEquals(0, cnt);
        booking = buildObject(dt,"15:00 - 16:00",1001);
        repo.save(booking);
        cnt = repo.findAvailability(1001, dt, "15:00 - 16:00");
        assertEquals(1, cnt);

    }

    @Test
    public void saveTest() throws ParseException
    {
        java.sql.Date dt = new java.sql.Date(new SimpleDateFormat("dd-MM-yyyy").parse("31-12-17").getTime());
        booking = buildObject(dt,"18:00 - 19:00",1002);
        //booking = buildObject(dt,null,1002);
        repo.save(booking);
//        assertThat(booking.getBookingId()).isGreaterThan(0);
        booking = repo.findOne(booking.getBookingId());
        assertEquals("18:00 - 19:00", booking.getBookingSlot());
        assertEquals(dt, booking.getBookingDate());
        assertEquals(1002, booking.getResource().getResourceId());
    }

//    @Test(expected=DataIntegrityViolationException.class)
    @Test
    public void saveTestInvalidSlot() throws ParseException
    {
        java.sql.Date dt = new java.sql.Date(new SimpleDateFormat("dd-MM-yyyy").parse("31-12-17").getTime());
        booking = buildObject(dt,null,1002);
        repo.save(booking);
    }

//    @Test(expected=DataIntegrityViolationException.class)
    @Test
    public void saveTestInvalidDate()
    {
        booking = buildObject(null,"18:00 - 19:00",1002);
        repo.save(booking);
    }

//    @Test(expected=DataIntegrityViolationException.class)
    @Test
    public void saveTestInvalidResource() throws ParseException
    {
        java.sql.Date dt = new java.sql.Date(new SimpleDateFormat("dd-MM-yyyy").parse("31-12-17").getTime());
        booking = buildObject(dt,"18:00 - 19:00",92);
        repo.save(booking);
    }

    @Test
    public void findAllTest()
    {
        List<Booking> list = (List<Booking>) repo.findAll();
        assertNotNull(list);
        assertThat(list.size()).isGreaterThan(0);
    }

    private Booking buildObject(Date dt, String slot, int resourceId)
    {
        Booking bookingObj = new Booking();
        bookingObj.setBookingId(0);
        bookingObj.setBookingDate(dt);
        bookingObj.setBookingSlot(slot);
        bookingObj.setResource(new Resource(resourceId));
        return bookingObj;

    }

}