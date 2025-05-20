package com.projects.airbnb.service;

import com.projects.airbnb.dto.HotelDto;

public interface HotelService {

    HotelDto createNewHotel(HotelDto hotelDto);

    HotelDto getHotelById(Long id);

    HotelDto updateHotelById(Long hotelId, HotelDto updatedHotel);

    void deleteHotelById(Long hotelId);

    void activateHotel(Long hotelId);
}
