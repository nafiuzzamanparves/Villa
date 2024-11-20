package com.ridoy.villa.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Entity
@Table(name = "customer")
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "q_id") // Qatar ID
    private String qid;

    @Column(name = "passport")
    private String passport;

    @Column(name = "security_money")
    private BigDecimal securityMoney;

    @Column(name = "deposit_type") // e.g., "Cash", "Check", "Credit Card"
    private String depositState;

    @Column(name = "collection_type") // Currently only "Monthly" is supported
    private String collectionType;

}
