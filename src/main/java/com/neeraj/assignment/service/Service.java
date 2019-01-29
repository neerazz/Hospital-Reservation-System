package com.neeraj.assignment.service;

import com.neeraj.assignment.data.BookingDTO;
import com.neeraj.assignment.exceptions.ResourceNotFoundException;
import com.neeraj.assignment.model.Booking;
import com.neeraj.assignment.model.Resource;
import com.neeraj.assignment.repository.BookingRepository;
import com.neeraj.assignment.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private BookingRepository bookingRepository;

    public List<Resource> getResources() {
        List<Resource> allResources = new ArrayList<>();
        resourceRepository.findAll().forEach(r -> allResources.add(r));
        return allResources;
    }

    public Resource getResources(Integer id) {
        Optional<Resource> tempResource = resourceRepository.findById(id);
        return tempResource.orElseThrow(() -> new ResourceNotFoundException("There are no any resources with id:" + id));
    }

    public void addResource(Resource resource) {
        resourceRepository.save(resource);
    }

    public void deleteResource(Resource resource) {
        resourceRepository.delete(resource);
    }

    public List<Booking> getReservations() {
        List<Booking> allReservations = new ArrayList<>();
        bookingRepository.findAll().forEach(b -> allReservations.add(b));
        return allReservations;
    }

    public void addReservation(BookingDTO bookingDTO) {

        if ( getResources(bookingDTO.getResourceId()) != null &&
                bookingRepository.findAlreadyBooked(getResources(bookingDTO.getResourceId()),bookingDTO.getBookingDate(),bookingDTO.getBookingSlot()) > 0) {
            bookingRepository.save(
                    new Booking()
                    .setBookingDate(bookingDate)
                    .setBookingSlot(bookingslot)
                    .setResource(getResources(resourceid))
            );
        }
    }
    public boolean resourceIsAvailable(int resourceid){
        return resourceRepository.findById(resourceid)
    }
}
