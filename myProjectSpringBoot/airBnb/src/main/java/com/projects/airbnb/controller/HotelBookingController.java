package com.projects.airbnb.controller;

import com.projects.airbnb.dto.BookingDto;
import com.projects.airbnb.dto.BookingRequest;
import com.projects.airbnb.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/bookings")
@RequiredArgsConstructor
public class HotelBookingController {

    private final BookingService bookingService;

    public ResponseEntity<BookingDto> initializeBooking(@RequestBody BookingRequest bookingRequest) {
       BookingDto bookingDto = bookingService.initializeBooking(bookingRequest);
       return ResponseEntity.ok(bookingDto);
    }
}
