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
    private Date bookingdate;

    @Column(name = "bookingslot")
    private String bookingslot;

    @ManyToOne
    @JoinColumn(name = "resourceid" , referencedColumnName = "resourceid")
    private Resource resource;

    public int getBookingId() {
        return bookingId;
    }
    public Booking setBookingId(int bookingId) {
        this.bookingId = bookingId;
        return this;
    }
    public String getBookingSlot() {
        return bookingslot;
    }
    public Booking setBookingSlot(String bookingSlot) {
        this.bookingslot = bookingSlot;
        return this;
    }
    public Resource getResource() {
        return resource;
    }
    public Booking setResource(Resource resource) {
        this.resource = resource;
        return this;
    }
    public Date getBookingDate() {
        return (Date) bookingdate.clone();
    }
    public Booking setBookingDate(Date bookingDate) {
        this.bookingdate = bookingDate!=null?(Date) bookingDate.clone():null;
        return this;
    }
    @Override
    public String toString() {
        return "Booking [bookingId=" + bookingId + ", bookingSlot=" + bookingslot + ", resource=" + resource + "]";
    }
}
