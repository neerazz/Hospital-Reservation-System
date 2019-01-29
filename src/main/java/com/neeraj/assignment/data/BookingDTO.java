package com.neeraj.assignment.data;

import java.sql.Date;

public class BookingDTO {
    private String bookingSlot;
    private String bookingDate;
    private int resourceId;

    public String getBookingSlot() {
        return bookingSlot;
    }
    public void setBookingSlot(String bookingSlot) {
        this.bookingSlot = bookingSlot;
    }

    public Date getBookingDate() {
        return Date.valueOf(bookingDate);
    }
    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }
    public int getResourceId() {
        return resourceId;
    }
    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }
}
