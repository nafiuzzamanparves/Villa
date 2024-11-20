package com.ridoy.villa.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @ManyToOne
    @JoinColumn(name = "villa_id", nullable = false)
    @JsonBackReference
    private Villa villa;

    @Column(name = "room_number", nullable = false)
    private String roomNumber;

    @Column(name = "type")
    private String type; // e.g., Single, Double, Suite

    @Column(name = "status")
    private String status; // e.g., Occupied, Available

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "rent_amount", nullable = false)
    private BigDecimal rentAmount = BigDecimal.ZERO;

    @Transient
    @JsonProperty("villaId")
    public Long getVillaId() {
        return this.villa != null ? this.villa.getVillaId() : null;
    }
}
