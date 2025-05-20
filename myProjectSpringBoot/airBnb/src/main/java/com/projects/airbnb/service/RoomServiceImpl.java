package com.projects.airbnb.service;

import com.projects.airbnb.dto.HotelDto;
import com.projects.airbnb.dto.RoomDto;
import com.projects.airbnb.entity.Hotel;
import com.projects.airbnb.entity.Room;
import com.projects.airbnb.exception.ResourceNotFoundException;
import com.projects.airbnb.repository.HotelRepository;
import com.projects.airbnb.repository.RoomRepository;
import com.projects.airbnb.utility.EntityFinder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final EntityFinder entityFinder;
    private final ModelMapper modelMapper;
    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;


    @Override
    public RoomDto createNewRoom(Long hotelId, RoomDto roomDto) {
        log.info("Create a new hotel with the ID: {}", hotelId);

        Hotel existingHotel = entityFinder.findByIdOrThrow(hotelRepository, hotelId, "Hotel");

        Room room = modelMapper.map(roomDto, Room.class);
        room.setHotel(existingHotel);

        Room savedRoom = roomRepository.save(room);
        return modelMapper.map(savedRoom, RoomDto.class);
    }

    @Override
    public List<RoomDto> getAllRoomsInHotel(Long hotelId) {
        log.info("Getting all rooms in hotel with the ID: {}", hotelId);

        Hotel existingHotel = entityFinder.findByIdOrThrow(hotelRepository, hotelId, "Hotel");

        return existingHotel.getRooms()
                .stream()
                .map(element -> modelMapper.map(element, RoomDto.class))
                .toList();
    }

    @Override
    public RoomDto getRoomById(Long roomId) {
        log.info("Getting the room ID: {}", roomId);
        Room existingRoom = entityFinder.findByIdOrThrow(roomRepository, roomId, "Room");
        return modelMapper.map(existingRoom, RoomDto.class);
    }

    @Override
    public void deleteRoomById(Long roomId) {
        log.info("Deleting the room ID: {}", roomId);
        roomRepository.findById(roomId)
                .ifPresentOrElse(roomRepository::delete,
                        () -> {
                            throw new ResourceNotFoundException("Room not found with the ID: " + roomId);
                        });

        //TODO: delete all future inventory
    }
}
