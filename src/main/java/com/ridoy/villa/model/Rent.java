package com.ridoy.villa.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

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
    private Customer customer;

    @Column(name = "month", nullable = false)
    private int month; // e.g., 1 for January, 2 for February

    @Column(name = "year", nullable = false)
    private int year; // e.g., 2024

    @Column(name = "total_rent", nullable = false)
    private BigDecimal totalRent;

    @Column(name = "amount_paid", nullable = false)
    @ColumnDefault("0.0")
    private BigDecimal amountPaid = BigDecimal.ZERO;

    @Column(name = "security_money_used")
    private BigDecimal securityMoneyUsed = BigDecimal.ZERO;

    @Column(name = "deposit_type") // e.g., "Cash", "Check", "Credit Card"
    @Enumerated(EnumType.STRING)
    private String depositState;

    @Column(name = "status", nullable = false)
    private String status; // e.g., "Paid", "Partial", "Unpaid"
}