package com.tcs.hack.dto;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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

    public Date getBookingDate() throws ParseException {
        return new java.sql.Date(new SimpleDateFormat("dd-MM-yyyy").parse("31-12-17").getTime());
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

    @Override
    public String toString() {
        return "BookingDTO{" +
                "bookingSlot='" + bookingSlot + '\'' +
                ", bookingDate='" + bookingDate + '\'' +
                ", resourceId=" + resourceId +
                '}';
    }
}
