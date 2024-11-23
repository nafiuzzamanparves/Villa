package com.ridoy.villa.repository;

import com.ridoy.villa.dto.RoomDTO;
import com.ridoy.villa.model.Customer;
import com.ridoy.villa.model.Room;
import com.ridoy.villa.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    Customer findCustomerByRoomId(Long roomId);

    List<Room> findByVilla_VillaId(Long villaId);

    @Query("""
            SELECT new com.ridoy.villa.dto.RoomDTO(
                r.roomId,\s
                r.roomNumber,\s
                r.type,\s
                r.status,\s
                r.rentAmount,\s
                r.villa.villaId,\s
                r.customer.customerId,\s
                r.createdAt,\s
                r.updatedAt,\s
                r.createdBy,\s
                r.updatedBy,\s
                r.isActive
            )\s
            FROM Room r\s
            WHERE r.villa.villaId = :villaId
            """)
    List<RoomDTO> findRoomDTOByVillaId(@Param("villaId") Long villaId);

    @Query("""
            SELECT new com.ridoy.villa.dto.RoomDTO(
                r.roomId,\s
                r.roomNumber,\s
                r.type,\s
                r.status,\s
                r.rentAmount,\s
                r.villa.villaId,\s
                r.customer.customerId,\s
                r.createdAt,\s
                r.updatedAt,\s
                r.createdBy,\s
                r.updatedBy,\s
                r.isActive
            )\s
            FROM Room r\s
            WHERE r.status = :status
            """)
    List<RoomDTO> findRoomsByStatus(@Param("status") Status status);

    Optional<Room> findRoomsByCustomer_CustomerId(Long id);
}
