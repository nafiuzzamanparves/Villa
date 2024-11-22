package com.ridoy.villa.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "villa")
public class Villa extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long villaId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "location")
    private String location;

    @Column(name = "description")
    private String description;

//    @OneToMany(mappedBy = "villa", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonManagedReference
//    private List<Room> rooms;

}
