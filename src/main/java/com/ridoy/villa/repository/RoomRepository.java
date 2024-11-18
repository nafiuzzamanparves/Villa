package com.ridoy.villa.repository;

import com.ridoy.villa.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    // No need for additional methods unless you want to add custom queries
}
