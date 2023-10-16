package org.ncu.hirewheels.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import org.ncu.hirewheels.entities.Booking;
import org.ncu.hirewheels.requests.BookingRequest;
import org.ncu.hirewheels.services.BookingService;


@RestController
@RequestMapping("/hirewheels/v1")
public class BookingController {

	@Autowired
    private BookingService bookingService;

    // Add a new booking
    @PostMapping(value = "/bookings", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Booking> addBooking(@RequestBody BookingRequest bookingRequest) {
    	
    	
    	Booking booking = bookingService.convertToEntity(bookingRequest);
    	
        // Call the service method to add the booking
        Booking addedBooking = bookingService.addBooking(booking);

        // Check if the booking was added successfully
        if (addedBooking != null) {
            // Return the added booking with HTTP status CREATED
            return new ResponseEntity<>(addedBooking, HttpStatus.CREATED);
        } else {
            // Return HTTP status BAD_REQUEST if the booking couldn't be added
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
