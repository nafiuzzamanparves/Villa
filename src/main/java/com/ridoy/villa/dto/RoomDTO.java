package com.ridoy.villa.dto;

import com.ridoy.villa.model.enums.RoomType;
import com.ridoy.villa.model.enums.Status;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class RoomDTO extends BaseDTO {
    private final Long roomId;
    private final String roomNumber;
    private final RoomType type;
    private final Status status;
    private final BigDecimal rentAmount;
    private final Long villaId;
    private final Long customerId;

    public RoomDTO(Long roomId, String roomNumber, RoomType type, Status status, BigDecimal rentAmount, Long villaId, Long customerId,
                   LocalDateTime createdAt, LocalDateTime updatedAt, String createdBy, String updatedBy, boolean isActive) {
        super(createdAt, updatedAt, createdBy, updatedBy, isActive);
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.type = type;
        this.status = status;
        this.rentAmount = rentAmount;
        this.villaId = villaId;
        this.customerId = customerId;
    }
}

