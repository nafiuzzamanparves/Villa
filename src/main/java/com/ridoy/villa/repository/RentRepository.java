package com.ridoy.villa.repository;

import com.ridoy.villa.model.Rent;
import com.ridoy.villa.model.enums.Month;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RentRepository extends JpaRepository<Rent, Long> {
    List<Rent> findByRoom_RoomId(Long roomId);

    @SuppressWarnings("unused")
    List<Rent> findByRoom_RoomIdAndMonthAndYear(Long roomId, Month month, int year);

    @Query("SELECT DISTINCT r.year FROM Rent r ORDER BY r.year")
    List<Integer> findDistinctYears();

    List<Rent> findByYearAndRoom_RoomId(Integer year, Long roomId);
}