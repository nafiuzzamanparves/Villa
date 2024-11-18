package com.ridoy.villa.controller;

import com.ridoy.villa.model.Villa;
import com.ridoy.villa.service.VillaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/villas")
public class VillaController {

    @Autowired
    private VillaService villaService;

    // Get all Villas
    @GetMapping
    public List<Villa> getAllVillas() {
        return villaService.getAllVillas();
    }

    // Get Villa by ID
    @GetMapping("/{id}")
    public ResponseEntity<Villa> getVillaById(@PathVariable Long id) {
        Villa villa = villaService.getVillaById(id);
        return ResponseEntity.ok(villa);
    }

    // Create a new Villa
    @PostMapping
    public ResponseEntity<Villa> createVilla(@RequestBody Villa villa) {
        Villa createdVilla = villaService.createVilla(villa);
        return ResponseEntity.ok(createdVilla);
    }

    // Update an existing Villa
    @PutMapping("/{id}")
    public ResponseEntity<Villa> updateVilla(@PathVariable Long id, @RequestBody Villa villaDetails) {
        Villa updatedVilla = villaService.updateVilla(id, villaDetails);
        return ResponseEntity.ok(updatedVilla);
    }

    // Delete a Villa
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVilla(@PathVariable Long id) {
        villaService.deleteVilla(id);
        return ResponseEntity.noContent().build();
    }
}
