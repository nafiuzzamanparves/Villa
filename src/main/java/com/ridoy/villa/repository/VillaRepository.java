package com.ridoy.villa.repository;

import com.ridoy.villa.model.Villa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VillaRepository extends JpaRepository<Villa, Long> {
    // JpaRepository provides basic CRUD operations, no need to define additional methods unless needed.
}
