package com.example.kwbruunauktion.auktionSystem.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class SpecificCar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false, unique = true)
    private String chassisNumber;

    @Column(length = 30, nullable = false)
    private String facModelCodeL;

    @Column(length = 30, nullable = false)
    private LocalDate firstRegDate;

    @Column(length = 30, nullable = false)
    private String model;

    @Column(length = 45, nullable = false)
    private String modelText;

    @Column(length = 30, nullable = false)
    private String origModelCode1;

    @Column(length = 30, nullable = false)
    private String modelYearCode;

    @Column(length = 30, nullable = false)
    private String colorCode;

    @Column(length = 30, nullable = false)
    private String trimCode;

    @Column(length = 30, nullable = false)
    private String regNumberImp;

    @Column(length = 30, nullable = false)
    private String regMonths;

    @Column(length = 30, nullable = false)
    private LocalDate returnDate;

    @Column(length = 30, nullable = false)
    private String holdPeriod;

    @OneToMany(mappedBy = "specificCar")
    @ToString.Exclude
    private List<CampaignBid> campaignBid;
}
