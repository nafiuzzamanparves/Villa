package com.ridoy.villa.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "rent")
public class Rent extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rentId;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer; // Optional since a room may not always have a customer

    @Column(name = "month_year", nullable = false)
    private String monthYear; // e.g., "2024-01" for January 2024

    @Column(name = "total_rent", nullable = false)
    private BigDecimal totalRent;

    @Column(name = "amount_paid", nullable = false)
    private BigDecimal amountPaid = BigDecimal.ZERO;

    @Column(name = "security_money_used")
    private BigDecimal securityMoneyUsed = BigDecimal.ZERO;

    @Column(name = "status", nullable = false)
    private String status; // e.g., "Paid", "Partial", "Unpaid"
}
