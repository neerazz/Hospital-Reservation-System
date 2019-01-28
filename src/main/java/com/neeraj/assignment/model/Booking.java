package com.neeraj.assignment.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int bookingId;

    @Column(nullable=false)
    private Date bookingDate;

    private String bookingSlot;

    @ManyToOne
    @JoinColumn(name = "resourceid" , referencedColumnName = "resourceid")
    private Resource resource;

    public int getBookingId() {
        return bookingId;
    }
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }
    public String getBookingSlot() {
        return bookingSlot;
    }
    public void setBookingSlot(String bookingSlot) {
        this.bookingSlot = bookingSlot;
    }
    public Resource getResource() {
        return resource;
    }
    public void setResource(Resource resource) {
        this.resource = resource;
    }
    public Date getBookingDate() {
        return (Date) bookingDate.clone();
    }
    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate!=null?(Date) bookingDate.clone():null;
    }
    @Override
    public String toString() {
        return "Booking [bookingId=" + bookingId + ", bookingSlot=" + bookingSlot + ", resource=" + resource + "]";
    }
}
