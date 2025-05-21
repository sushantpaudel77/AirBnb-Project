package com.projects.airbnb.dto;

import com.projects.airbnb.entity.Guest;
import com.projects.airbnb.entity.Hotel;
import com.projects.airbnb.entity.Room;
import com.projects.airbnb.entity.User;
import com.projects.airbnb.entity.enums.BookingStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public class BookingDto {
    private Long id;
    private Hotel hotel;
    private Room room;
    private User user;
    private Integer roomsCount;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private BookingStatus bookingStatus;
    private Set<Guest> guests;
}
