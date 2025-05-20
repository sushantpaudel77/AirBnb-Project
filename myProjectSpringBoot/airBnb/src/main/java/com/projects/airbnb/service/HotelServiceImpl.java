package com.projects.airbnb.service;

import com.projects.airbnb.dto.HotelDto;
import com.projects.airbnb.entity.Hotel;
import com.projects.airbnb.exception.ResourceNotFoundException;
import com.projects.airbnb.repository.HotelRepository;
import com.projects.airbnb.utility.EntityFinder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;
    private final EntityFinder entityFinder;

    @Override
    public HotelDto createNewHotel(HotelDto hotelDto) {
        log.info("Creating a new hotel with name: {}", hotelDto.getName());

        Hotel hotel = modelMapper.map(hotelDto, Hotel.class);
        hotel.setIsActive(false);

        hotel = hotelRepository.save(hotel);

        log.info("Created a new hotel with ID: {}", hotel.getId());
        return modelMapper.map(hotel, HotelDto.class);
    }

    @Override
    public HotelDto getHotelById(Long id) {
        log.info("Getting the hotel with ID: {}", id);
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException
                        ("Hotel not found with ID: " + id));
        return modelMapper.map(hotel, HotelDto.class);
    }

    @Override
    public HotelDto updateHotelById(Long hotelId, HotelDto updatedHotel) {
        Hotel existingHotel = entityFinder.findByIdOrThrow(hotelRepository, hotelId, "Hotel");

        // Map new values over the existing object
        modelMapper.map(updatedHotel, existingHotel);
        existingHotel.setId(hotelId);

        Hotel savedHotel = hotelRepository.save(existingHotel);
        return modelMapper.map(savedHotel, HotelDto.class);
    }

    @Override
    public void deleteHotelById(Long hotelId) {
        hotelRepository.findById(hotelId)
                .ifPresentOrElse(hotelRepository::delete,
                        () -> {
                            throw new ResourceNotFoundException("Hotel not found with the ID: " + hotelId);
                        });
        //TODO: delete the future inventories for the hotel
    }

    @Override
    public void activateHotel(Long hotelId) {
        log.info("Activating the hotel with ID: {}", hotelId);
        Hotel existingHotel = entityFinder.findByIdOrThrow(hotelRepository, hotelId, "Hotel");

        existingHotel.setIsActive(true);
        //TODO: Create a inventory for all the rooms for this hotel
    }
}
