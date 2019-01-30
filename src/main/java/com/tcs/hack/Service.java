package com.tcs.hack;

import com.tcs.hack.dto.BookingDTO;
import com.tcs.hack.dto.ReservationsDTO;
import com.tcs.hack.model.Booking;
import com.tcs.hack.model.Resource;
import com.tcs.hack.repository.BookingRepository;
import com.tcs.hack.repository.ResourceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<Resource> getResources() {
        List<Resource> allResources = new ArrayList<>();
        resourceRepository.findAll().forEach(r -> allResources.add(r));
        return allResources;
    }

    public Resource getResources(Integer resourceId) {
        Optional<Resource> tempResource = resourceRepository.findById(resourceId);
        return tempResource.get();
    }

    public void addResource(Resource resource) {
        resourceRepository.save(resource);
    }

    public void deleteResource(Resource resource) {
        resourceRepository.delete(resource);
    }

    public List<ReservationsDTO> getReservations() {
        List<Booking> allReservations = new ArrayList<>();
        bookingRepository
                .findAll()
                .forEach(b -> allReservations.add(b));

        return allReservations
                .stream()
                .map(b -> convertToReservationsDTO(b))
                .collect(Collectors.toList());
    }

    public void addReservation(BookingDTO bookingDTO) throws ParseException {

        if ( getResources(bookingDTO.getResourceId()) != null &&
                bookingRepository.findAvailability(bookingDTO.getResourceId(),bookingDTO.getBookingDate(),bookingDTO.getBookingSlot()) == 0) {
            bookingRepository.save(convertToBooking(bookingDTO));
        }
    }

    private BookingDTO convertToBookingDTO(Booking booking){
//        System.out.println("Recieved booking: \t " + booking);
        BookingDTO convertedBookingDTO = new BookingDTO();
        BeanUtils.copyProperties(booking, convertedBookingDTO);
//        System.out.println("Recieved convertedBookingDTO: \t " + convertedBookingDTO);
        return convertedBookingDTO;
    }

    private ReservationsDTO convertToReservationsDTO(Booking booking){
//        System.out.println("Recieved booking: \t " + booking);

        ReservationsDTO convertedReservationsDTO = modelMapper.map(booking, ReservationsDTO.class);
//        ReservationsDTO convertedReservationsDTO = new ReservationsDTO();
//        BeanUtils.copyProperties(booking,convertedReservationsDTO);
//        System.out.println("Recieved convertedBookingDTO: \t " + convertedReservationsDTO);
        return convertedReservationsDTO;
    }

    private Booking convertToBooking(BookingDTO bookingDTO){
//        System.out.println("Recieved bookingDTO: \t " + bookingDTO);
        Booking convertedBooking = new Booking();
        BeanUtils.copyProperties(bookingDTO, convertedBooking);
//        System.out.println("Recieved convertedBooking: \t " + convertedBooking);
        return convertedBooking;
    }
}
