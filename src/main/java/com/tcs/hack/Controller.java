package com.tcs.hack;

import com.tcs.hack.dto.BookingDTO;
import com.tcs.hack.dto.ReservationsDTO;
import com.tcs.hack.model.Resource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/tcs/hack/v1")
public class Controller {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

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
    public void addResource(@RequestBody Resource resource) {
        service.addResource(resource);
    }

    //    Delete a new resource
    @DeleteMapping("/resources")
    public void deleteResource(@RequestParam Resource resource) {
        service.deleteResource(resource);
    }

    //Fetch all the reservations
    @GetMapping("/reservations")
    public List<ReservationsDTO> getreservations() {
        return service.getReservations();
    }

    //Add a new reservation
    @PostMapping("/reservations")
    public void addReservations(@RequestParam BookingDTO bookingDTO) throws ParseException {
        service.addReservation(bookingDTO);
    }
}
