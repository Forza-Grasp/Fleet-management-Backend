package com.example.kwbruunauktion.auktionSystem.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
public class UserLeaser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String firstName;

    @Column(length = 100, nullable = false)
    private String lastName;

    @Column(length = 10, nullable = false)
    private String phoneNumber;

    @Column(length = 100, nullable = false)
    private String companyName;

    @Column(length = 100, nullable = false)
    private String companyEuVatNumber;

    @Column(length = 10, nullable = false)
    private String addressLine1;

    @Column(length = 100, nullable = false)
    private String addressLine2;

    @Column(length = 100, nullable = false)
    private String city;

    @Column(length = 100, nullable = false)
    private String postalCode;

    @Column(length = 100, nullable = false)
    private String country;

    @JoinTable(name = "viewableCarBrands")
    @ManyToMany(cascade = CascadeType.ALL)
    @ToString.Exclude
    List<SpecificCarModel> viewableCarBrands;

    @OneToOne(cascade = CascadeType.ALL)
    private Ownership ownership;
}
