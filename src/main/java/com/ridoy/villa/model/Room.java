package com.ridoy.villa.model;

import com.ridoy.villa.model.enums.RoomType;
import com.ridoy.villa.model.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "room")
public class Room extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    @ManyToOne()
    @JoinColumn(name = "villa_id", nullable = false)
    private Villa villa;

    @Column(name = "room_number", nullable = false)
    private String roomNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private RoomType type; // e.g., Single, Double, Suite

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status; // e.g., Occupied, Available

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "rent_amount", nullable = false)
    private BigDecimal rentAmount = BigDecimal.ZERO;

//    @Transient
//    @JsonProperty("villaId")
//    public Long getVillaId() {
//        return this.villa != null ? this.villa.getVillaId() : null;
//    }
}
