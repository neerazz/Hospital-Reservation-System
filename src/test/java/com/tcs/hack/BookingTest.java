package com.tcs.hack;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcs.hack.dto.BookingDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
//@WebAppConfiguration
public class BookingTest {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;
    private BookingDTO booking;
    private String path="/tcs/hack/v1";

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    //view bookings
    @Test
    public void getBookingTest() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get(path+"/reservations").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$[0].resourceName").isString())
                .andExpect(jsonPath("$[0].bookingDate").isString())
                .andExpect(jsonPath("$[0].bookingSlot").isString());
    }

    //add bookings
    @Test
    public void addBookingTest() throws Exception {

        booking = new BookingDTO();
        booking.setResourceId(1001);
        booking.setBookingDate("25-12-2017");
        booking.setBookingSlot("15:00 - 16:00");

        mvc.perform(MockMvcRequestBuilders.post(path+"/reservations")
                .content(toJson(booking))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                //.andDo(print())
                .andExpect(status().is(400));
//                .andExpect(jsonPath("$.bookingId").isEmpty());
    }

    //block if already booked
    @Test
    public void blockBookingTest() throws Exception {

        booking = new BookingDTO();
        booking.setResourceId(1001);
        booking.setBookingDate("26-12-2017");
        booking.setBookingSlot("11:00 - 12:00");

        System.out.println(booking);
        //add first time
        mvc.perform(MockMvcRequestBuilders.post(path+"/reservations")
                .content(toJson(booking))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.bookingId").isNumber());

        //block next
        mvc.perform(MockMvcRequestBuilders.post(path+"/reservations")
                .content(toJson(booking))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
//                .andExpect(status().isOk())
//                .andExpect(content().string("Resource not available"));
    }

    private byte[] toJson(Object r) throws Exception {
        ObjectMapper map = new ObjectMapper();
        return map.writeValueAsString(r).getBytes();
    }
}
