package com.ridoy.villa.controller;

import com.ridoy.villa.model.Room;
import com.ridoy.villa.service.RoomService;
import com.ridoy.villa.util.ApiResponse;
import com.ridoy.villa.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    // Get all Rooms
    @GetMapping
    public ResponseEntity<ApiResponse<?>> getAllRooms() {
        try {
            List<Room> rooms = roomService.getAllRooms();
            if (rooms != null && !rooms.isEmpty()) {
                return ResponseEntity.ok(ResponseUtil.success("Rooms retrieved successfully", rooms));
            } else {
                return ResponseEntity.status(404).body(ResponseUtil.failed("No rooms found", new ArrayList<>()));
            }
        } catch (Exception e) {
            return ResponseEntity.status(400).body(ResponseUtil.failed("Rooms retrieval failed", e.getMessage()));
        }
    }

    // Get a Room by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Room>> getRoomById(@PathVariable Long id) {
        Room room = roomService.getRoomById(id);
        if (room != null) {
            return ResponseEntity.ok(ResponseUtil.success("Room found", room));
        } else {
            return ResponseEntity.status(404).body(ResponseUtil.failed("Room not found", null));
        }
    }

    // Create a new Room
    @PostMapping
    public ResponseEntity<ApiResponse<?>> createRoom(@RequestBody Room room) {
        try {
            Room createdRoom = roomService.createRoom(room);
            if (createdRoom != null) {
                return ResponseEntity.ok(ResponseUtil.success("Room created successfully", createdRoom));
            } else {
                return ResponseEntity.status(400).body(ResponseUtil.failed("Room creation failed", null));
            }
        } catch (Exception e) {
            return ResponseEntity.status(400).body(ResponseUtil.failed("Room creation failed", e.getMessage()));
        }
    }

    // Update an existing Room
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Room>> updateRoom(@PathVariable Long id, @RequestBody Room roomDetails) {
        try {
            Room updatedRoom = roomService.updateRoom(id, roomDetails);
            return ResponseEntity.ok(ResponseUtil.success("Room updated successfully", updatedRoom));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(ResponseUtil.failed("Room not found for update", null));
        }
    }

    // Delete a Room by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteRoom(@PathVariable Long id) {
        try {
            roomService.deleteRoom(id);
            return ResponseEntity.ok(ResponseUtil.success("Room deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(ResponseUtil.failed("Room not found for deletion", null));
        }
    }
}