package com.ridoy.villa.controller;

import com.ridoy.villa.model.Villa;
import com.ridoy.villa.service.VillaService;
import com.ridoy.villa.util.ApiResponse;
import com.ridoy.villa.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/villas")
public class VillaController {

    @Autowired
    private VillaService villaService;

    // Get all Villas
    @GetMapping
    public ResponseEntity<ApiResponse<?>> getAllVillas() {
        try {
            List<Villa> villas = villaService.getAllVillas();
            if (villas != null && !villas.isEmpty()) {
                return ResponseEntity.ok(ResponseUtil.success("Villas retrieved successfully", villas));
            } else {
                return ResponseEntity.status(404).body(ResponseUtil.success("No villas found", new ArrayList<>()));
            }
        } catch (Exception e) {
            return ResponseEntity.status(400).body(ResponseUtil.failed("Villas retrieval failed", e.getMessage()));
        }
    }

    // Get Villa by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Villa>> getVillaById(@PathVariable Long id) {
        Villa villa = villaService.getVillaById(id);
        if (villa != null) {
            return ResponseEntity.ok(ResponseUtil.success("Villa found", villa));
        } else {
            return ResponseEntity.status(404).body(ResponseUtil.failed("Villa not found", null));
        }
    }

    // Create a new Villa
    @PostMapping
    public ResponseEntity<ApiResponse<?>> createVilla(@RequestBody Villa villa) {
        try {
            Villa createdVilla = villaService.createVilla(villa);
            if (createdVilla != null) {
                return ResponseEntity.ok(ResponseUtil.success("Villa created successfully", createdVilla));
            } else {
                return ResponseEntity.status(400).body(ResponseUtil.failed("Villa creation failed", null));
            }
        } catch (Exception e) {
            return ResponseEntity.status(400).body(ResponseUtil.failed("Villa creation failed", e.getMessage()));
        }
    }

    // Update an existing Villa
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Villa>> updateVilla(@PathVariable Long id, @RequestBody Villa villaDetails) {
        try {
            Villa updatedVilla = villaService.updateVilla(id, villaDetails);
            return ResponseEntity.ok(ResponseUtil.success("Villa updated successfully", updatedVilla));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(ResponseUtil.failed("Villa not found for update", null));
        }
    }

    // Delete a Villa
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteVilla(@PathVariable Long id) {
        try {
            villaService.deleteVilla(id);
            return ResponseEntity.ok(ResponseUtil.success("Villa deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(ResponseUtil.failed("Villa not found for deletion", null));
        }
    }
}