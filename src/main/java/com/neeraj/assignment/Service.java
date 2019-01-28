package com.neeraj.assignment;

import com.neeraj.assignment.exceptions.ResourceNotFoundException;
import com.neeraj.assignment.model.Booking;
import com.neeraj.assignment.model.Resource;
import com.neeraj.assignment.repository.BookingRepository;
import com.neeraj.assignment.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private BookingRepository bookingRepository;

    public List<Resource> getresources() {
        List<Resource> allResources = new ArrayList<>();
        resourceRepository.findAll().forEach(r -> allResources.add(r));
        return allResources;
    }

    public Resource getresources(Integer id) {
        Optional<Resource> tempResource = resourceRepository.findById(id);
        return tempResource.orElseThrow(() -> new ResourceNotFoundException("There are no any resources with id:" + id));
    }

    public void addResource(Resource resource) {
        resourceRepository.save(resource);
    }

    public void deleteResource(Resource resource) {
        resourceRepository.delete(resource);
    }

    public List<Booking> getreservations() {
        List<Booking> allreservations = new ArrayList<>();
        bookingRepository.findAll().forEach(b -> allreservations.add(b));
        return allreservations;
    }

    public void addReservation(Booking booking) {
        bookingRepository.save(booking);
    }
}
