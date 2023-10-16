package org.ncu.hirewheels.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.ncu.hirewheels.entities.Location;
import org.ncu.hirewheels.entities.Users;
import org.ncu.hirewheels.entities.Vehicle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {
    private Long bookingId;
    private LocalDate pickupDate;
    private LocalDate dropoffDate;
    private LocalDate bookingDate;
    private BigDecimal amount;
    private Location location;
    private Vehicle vehicle;
    private Users user;

    // Constructors, getters, and setters

//    public BookingDto() {
//        // Default constructor
//    }
//
//    public BookingDto(
//        Long bookingId,
//        LocalDate pickupDate,
//        LocalDate dropoffDate,
//        LocalDate bookingDate,
//        BigDecimal amount,
//        Location location,
//        Vehicle vehicle,
//        Users user
//    ) {
//        this.bookingId = bookingId;
//        this.pickupDate = pickupDate;
//        this.dropoffDate = dropoffDate;
//        this.bookingDate = bookingDate;
//        this.amount = amount;
//        this.location = location;
//        this.vehicle = vehicle;
//        this.user = user;
//    }

    // Rest of the code (getters and setters)
}
