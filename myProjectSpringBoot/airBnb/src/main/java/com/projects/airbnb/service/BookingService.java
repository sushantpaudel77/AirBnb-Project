package com.projects.airbnb.service;

import com.projects.airbnb.dto.BookingDto;
import com.projects.airbnb.dto.BookingRequest;

public interface BookingService {

    BookingDto initializeBooking(BookingRequest bookingRequest);
}
