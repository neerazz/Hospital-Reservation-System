package com.neeraj.assignment.controller;

import com.neeraj.assignment.service.Service;
import com.neeraj.assignment.model.Booking;
import com.neeraj.assignment.model.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/tcs/hack/v1")
public class Controller {

    @Autowired
    private Service service;

    //    Fetch all available resources
    @GetMapping("/resources")
    public List<Resource> getresources() {
        return service.getResources();
    }

    //    Fetch only a specific resource
    @GetMapping("/resources/{id}")
    public Resource getresource(Integer id) {
        return service.getResources(id);
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
        return service.getReservations();
    }

    //Add a new reservation
    @PostMapping("/reservations")
    public void addReservations(@RequestParam("resourceid") int resourceid,
                                @RequestParam("bookingDate")java.sql.Date bookingDate,
                                @RequestParam("bookingslot") String bookingslot) {
        service.addReservation(resourceid,bookingDate,bookingslot);
    }
}
