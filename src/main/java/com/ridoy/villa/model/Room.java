package com.ridoy.villa.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    private Villa villa;

    @Column(name = "room_number", nullable = false)
    private String roomNumber;

    @Column(name = "type")
    private String type; // e.g., Single, Double, Suite

    @Column(name = "status", nullable = false)
    private String status; // e.g., Occupied, Available

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
