package com.neeraj.assignment.controller;

import com.neeraj.assignment.Service;
import com.neeraj.assignment.model.Booking;
import com.neeraj.assignment.model.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tcs/hack/v1")
public class Controller {

    @Autowired
    private Service service;

    //    Fetch all available resources
    @GetMapping("/resources")
    public List<Resource> getresources() {
        return service.getresources();
    }

    //    Fetch only a specific resource
    @GetMapping("/resources/{id}")
    public Resource getresource(Integer id) {
        return service.getresources(id);
    }

    //    Add/Update a new resource
    @PostMapping("/resources")
    public void addResource(@RequestParam Resource resource) {
        service.addResource(resource);
    }

    //    Delete a new resource
    @DeleteMapping("/resources")
    public void deleteResource(@RequestParam Resource resource) {
        service.deleteResource(resource);
    }

    //Fetch all the reservations
    @GetMapping("/reservations")
    public List<Booking> getreservations() {
        return service.getreservations();
    }

    //Add a new reservation
    @PostMapping("/reservations")
    public void addreservations(@RequestParam Booking booking) {
        service.addReservation(booking);
    }
}
