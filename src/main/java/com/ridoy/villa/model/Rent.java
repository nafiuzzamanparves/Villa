package com.ridoy.villa.model;

import com.ridoy.villa.model.enums.Month;
import com.ridoy.villa.model.enums.PaidStatus;
import com.ridoy.villa.model.enums.PaymentType;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "month", nullable = false)
    private Month month; // e.g., "January", "February", "March"

    @Column(name = "year", nullable = false)
    private int year; // e.g., 2024

    @Column(name = "total_rent", nullable = false)
    private BigDecimal totalRent;

    @Column(name = "amount_paid", nullable = false)
    @ColumnDefault("0.0")
    private BigDecimal amountPaid = BigDecimal.ZERO;

    @Column(name = "is_security_money_used", nullable = false)
    private boolean isSecurityMoneyUsed;

    @Column(name = "deposit_type") // e.g., "Cash", "Check", "Credit Card"
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Column(name = "paidStatus", nullable = false)
    @Enumerated(EnumType.STRING)
    private PaidStatus paidStatus; // e.g., "Paid", "Partial", "Unpaid"
}