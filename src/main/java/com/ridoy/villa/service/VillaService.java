package com.ridoy.villa.service;

import com.ridoy.villa.model.Villa;
import com.ridoy.villa.repository.VillaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VillaService {

    @Autowired
    private VillaRepository villaRepository;

    // Get all Villas
    public List<Villa> getAllVillas() {
        return villaRepository.findAll();
    }

    // Get Villa by ID
    public Villa getVillaById(Long id) {
        return villaRepository.findById(id).orElseThrow(() -> new RuntimeException("Villa not found with id: " + id));
    }

    // Create a new Villa
    public Villa createVilla(Villa villa) {
        return villaRepository.save(villa);
    }

    // Update an existing Villa
    public Villa updateVilla(Long id, Villa villaDetails) {
        Villa villa = villaRepository.findById(id).orElseThrow(() -> new RuntimeException("Villa not found with id: " + id));
        villa.setName(villaDetails.getName());
        villa.setLocation(villaDetails.getLocation());
        villa.setDescription(villaDetails.getDescription());
        villa.setRooms(villaDetails.getRooms());
        return villaRepository.save(villa);
    }

    // Delete a Villa
    public void deleteVilla(Long id) {
        Villa villa = villaRepository.findById(id).orElseThrow(() -> new RuntimeException("Villa not found with id: " + id));
        villaRepository.delete(villa);
    }
}
