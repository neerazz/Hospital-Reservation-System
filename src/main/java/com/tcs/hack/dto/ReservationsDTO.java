package com.tcs.hack.dto;

public class ReservationsDTO {

    private String resourceName;
    private String bookingDate;
    private String bookingSlot;

    public ReservationsDTO() {
    }

    public ReservationsDTO(String resourceName, String bookingDate, String bookingSlot) {
        this.resourceName = resourceName;
        this.bookingDate = bookingDate;
        this.bookingSlot = bookingSlot;
    }

    public String getBookingSlot() {
        return bookingSlot;
    }
    public ReservationsDTO setBookingSlot(String bookingSlot) {
        this.bookingSlot = bookingSlot;
        return this;
    }
    public String getBookingDate() {
        return bookingDate;
    }
    public ReservationsDTO setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
        return this;
    }
    public String getResourceName() {
        return resourceName;
    }
    public ReservationsDTO setResourceName(String resourceName) {
        this.resourceName = resourceName;
        return this;
    }

    @Override
    public String toString() {
        return "ReservationsDTO{" +
                "resourceName='" + resourceName + '\'' +
                ", bookingDate='" + bookingDate + '\'' +
                ", bookingSlot='" + bookingSlot + '\'' +
                '}';
    }
}
