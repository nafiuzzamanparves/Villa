package com.ridoy.villa.controller;

import com.ridoy.villa.model.Rent;
import com.ridoy.villa.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rents")
public class RentController {

    @Autowired
    private RentService rentService;

    // Get all Rent records for a specific Room
    @GetMapping("/room/{roomId}")
    public ResponseEntity<List<Rent>> getRentsByRoomId(@PathVariable Long roomId) {
        List<Rent> rents = rentService.getRentsByRoomId(roomId);
        return ResponseEntity.ok(rents);
    }

    // Create a new Rent record
    @PostMapping
    public ResponseEntity<Rent> createRent(@RequestBody Rent rent) {
        Rent createdRent = rentService.createRent(rent);
        return ResponseEntity.ok(createdRent);
    }

    // Update an existing Rent record
    @PutMapping("/{rentId}")
    public ResponseEntity<Rent> updateRent(@PathVariable Long rentId, @RequestBody Rent rentDetails) {
        Rent updatedRent = rentService.updateRent(rentId, rentDetails);
        return ResponseEntity.ok(updatedRent);
    }

    // Delete a Rent record by ID
    /*@DeleteMapping("/{rentId}")
    public ResponseEntity<Void> deleteRent(@PathVariable Long rentId) {
        rentService.deleteRent(rentId);
        return ResponseEntity.noContent().build();
    }*/
}
