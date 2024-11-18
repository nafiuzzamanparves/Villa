package com.ridoy.villa.service;

import com.ridoy.villa.model.Room;
import com.ridoy.villa.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    // Get all Rooms
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    // Get a Room by ID
    public Room getRoomById(Long id) {
        return roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Room not found with id: " + id));
    }

    // Create a new Room
    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

    // Update an existing Room
    public Room updateRoom(Long id, Room roomDetails) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Room not found with id: " + id));
        room.setRoomNumber(roomDetails.getRoomNumber());
        room.setType(roomDetails.getType());
        room.setStatus(roomDetails.getStatus());
        room.setVilla(roomDetails.getVilla());
        room.setCustomer(roomDetails.getCustomer());
        return roomRepository.save(room);
    }

    // Delete a Room by ID
    public void deleteRoom(Long id) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Room not found with id: " + id));
        roomRepository.delete(room);
    }
}
