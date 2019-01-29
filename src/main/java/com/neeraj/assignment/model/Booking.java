package com.neeraj.assignment.model;

import javax.persistence.*;
import java.sql.Date;

@Entity(name = "booking")
public class Booking {

    public Booking() {
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "bookingid")
    private int bookingId;

    @Column(nullable=false, name = "bookingdate")
    private Date bookingDate;

    @Column(name = "bookingslot")
    private String bookingSlot;

    @ManyToOne
    @JoinColumn(name = "resourceid" , referencedColumnName = "resourceid")
    private int resourceid;

    public int getBookingId() {
        return bookingId;
    }
    public Booking setBookingId(int bookingId) {
        this.bookingId = bookingId;
        return this;
    }
    public String getBookingSlot() {
        return bookingSlot;
    }
    public Booking setBookingSlot(String bookingSlot) {
        this.bookingSlot = bookingSlot;
        return this;
    }
    public int getResourceId() {
        return resourceid;
    }
    public Booking setResource(int resourceid) {
        this.resourceid = resourceid;
        return this;
    }
    public Date getBookingDate() {
        return (Date) bookingDate.clone();
    }
    public Booking setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate!=null?(Date) bookingDate.clone():null;
        return this;
    }
    @Override
    public String toString() {
        return "Booking [bookingId=" + bookingId + ", bookingSlot=" + bookingSlot + ", resource=" + resourceid + "]";
    }
}
