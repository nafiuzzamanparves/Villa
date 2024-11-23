package com.ridoy.villa.controller;

import com.ridoy.villa.model.Rent;
import com.ridoy.villa.service.RentService;
import com.ridoy.villa.util.ApiResponse;
import com.ridoy.villa.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/rents")
public class RentController {

    @Autowired
    private RentService rentService;

    @GetMapping()
    public ResponseEntity<ApiResponse<?>> getAllRents() {
        try {
            List<Rent> rents = rentService.getAllRents();
            if (rents != null && !rents.isEmpty()) {
                return ResponseEntity.ok(ResponseUtil.success("Rents retrieved successfully", rents));
            } else {
                return ResponseEntity.ok(ResponseUtil.success("No rents found", new ArrayList<>()));
            }
        } catch (Exception e) {
            return ResponseEntity.status(400).body(ResponseUtil.failed("Rents retrieval failed", e.getMessage()));
        }
    }

    // Get all Rent records for a specific Room
    @GetMapping("/byRoom/{roomId}")
    public ResponseEntity<ApiResponse<?>> getRentsByRoomId(@PathVariable Long roomId) {
        try {
            List<Rent> rents = rentService.getRentsByRoomId(roomId);
            if (rents != null && !rents.isEmpty()) {
                return ResponseEntity.ok(ResponseUtil.success("Rents retrieved successfully", rents));
            } else {
                return ResponseEntity.ok(ResponseUtil.success("No rents found for this room", new ArrayList<>()));
            }
        } catch (Exception e) {
            return ResponseEntity.status(400).body(ResponseUtil.failed("Rents retrieval failed", e.getMessage()));
        }
    }

    @GetMapping("/byYearAndRoomId")
    public ResponseEntity<ApiResponse<?>> getRentsByRoomIdAndYear(@RequestParam Long roomId, @RequestParam Integer year) {
        try {
            List<Rent> rents = rentService.getRentsByRoomIdAndYear(roomId, year);
            if (rents != null && !rents.isEmpty()) {
                return ResponseEntity.ok(ResponseUtil.success("Rents retrieved successfully", rents));
            } else {
                return ResponseEntity.ok(ResponseUtil.failed("No rents found for this room in the given year", new ArrayList<>()));
            }
        } catch (Exception e) {
            return ResponseEntity.status(400).body(ResponseUtil.failed("Rents retrieval failed", e.getMessage()));
        }
    }

    // Create a new Rent record
    @PostMapping
    public ResponseEntity<ApiResponse<?>> createRent(@RequestBody Rent rent) {
        try {
            Rent createdRent = rentService.createRent(rent);
            if (createdRent != null) {
                return ResponseEntity.ok(ResponseUtil.success("Rent created successfully", createdRent));
            } else {
                return ResponseEntity.status(400).body(ResponseUtil.success("Rent creation failed", null));
            }
        } catch (Exception e) {
            return ResponseEntity.status(400).body(ResponseUtil.failed("Rent creation failed", e.getMessage()));
        }
    }

    // Update an existing Rent record
    @PutMapping("/{rentId}")
    public ResponseEntity<ApiResponse<Rent>> updateRent(@PathVariable Long rentId, @RequestBody Rent rentDetails) {
        try {
            Rent updatedRent = rentService.updateRent(rentId, rentDetails);
            return ResponseEntity.ok(ResponseUtil.success("Rent updated successfully", updatedRent));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(ResponseUtil.failed("Rent not found for update", null));
        }
    }

    // Delete a Rent record by ID
    /*@DeleteMapping("/{rentId}")
    public ResponseEntity<ApiResponse<Void>> deleteRent(@PathVariable Long rentId) {
        try {
            rentService.deleteRent(rentId);
            return ResponseEntity.ok(ResponseUtil.success("Rent deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(ResponseUtil.failed("Rent not found for deletion", null));
        }
    }*/

    @GetMapping("/distinct-years")
    public ResponseEntity<ApiResponse<List<Integer>>> getDistinctYears() {
        return ResponseEntity.ok(ResponseUtil.success("Distinct Years retrieved successfully", rentService.getDistinctYears()));
    }
}