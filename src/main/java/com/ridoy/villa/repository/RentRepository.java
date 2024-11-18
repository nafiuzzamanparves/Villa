package com.ridoy.villa.repository;

import com.ridoy.villa.model.Rent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentRepository extends JpaRepository<Rent, Long> {
    List<Rent> findByRoom_RoomId(Long roomId);
}