package com.ridoy.villa.service;

import com.ridoy.villa.dto.RoomDTO;
import com.ridoy.villa.model.Room;
import com.ridoy.villa.model.enums.Status;
import com.ridoy.villa.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    // Get all Rooms
    public List<RoomDTO> getAllRooms() {
        return roomRepository.findAllRooms();
    }


    public RoomDTO getRoomById(Long id) {
        return roomRepository.findByRoomId(id)
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + id));


    }

    // Create a new Room
    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

    // Update an existing Room
    public Room updateRoom(Long id, Room roomDetails) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Room not found with id: " + id));

        if (roomDetails.getRoomNumber() != null)
            room.setRoomNumber(roomDetails.getRoomNumber());
        if (roomDetails.getRentAmount() != null)
            room.setRentAmount(roomDetails.getRentAmount());
        if (roomDetails.getType() != null)
            room.setType(roomDetails.getType());
        if (roomDetails.getStatus() != null)
            room.setStatus(roomDetails.getStatus());
        if (roomDetails.getVilla() != null)
            room.setVilla(roomDetails.getVilla());
        if (roomDetails.getCustomer() != null)
            room.setCustomer(roomDetails.getCustomer());

        return roomRepository.save(room);
    }

    // Delete a Room by ID
    public void deleteRoom(Long id) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Room not found with id: " + id));
        roomRepository.delete(room);
    }

    @SuppressWarnings("unused")
    public List<Room> getRoomsByVillaId(Long villaId) {
        return roomRepository.findByVilla_VillaId(villaId);
    }

    public List<RoomDTO> getRoomDTOByVillaId(Long villaId) {
        return roomRepository.findRoomDTOByVillaId(villaId);
    }

    public List<RoomDTO> getAvailableRooms() {
        return roomRepository.findRoomsByStatus(Status.AVAILABLE);
    }
}


