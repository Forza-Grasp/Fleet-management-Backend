package com.example.kwbruunauktion.auktionSystem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

@Entity
public class CampaignCar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100)
    private String brand;
    @Column(nullable = false, length = 100)
    private String model;
    @Column(nullable = false, length = 300)
    private String modelText;
    @Column(nullable = false, length = 100)
    private String description;
    @Column(nullable = false, length = 100)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate exceptedRegistrationFromDate;
    @Column(nullable = false, length = 100)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate exceptedRegistrationToDate;
    @Column(nullable = false, length = 10)
    private int monthsRegistered;
    @Column(nullable = false, length = 100)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate earliestExceptedReturnDate;
    @Column(nullable = false, length = 100)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate latestExceptedReturnDate;
    @Column(nullable = false, length = 100)
    private String mileage;
    @Column(nullable = false, length = 100)
    private String depositPerCar;
    @Column(nullable = false, length = 100)
    private String damageAndMileage;
    @Column(nullable = false, length = 100)
    private String supplyingConditions;
    @Column(nullable = false, length = 100)
    private String campaignPictureOne;
    @Column(nullable = false, length = 100)
    private String campaignPictureTwo;
    @Column(nullable = false, length = 100)
    private String campaignPictureThree;
    @Column(nullable = false, length = 100)
    private String campaignPictureFour;
    @Column(nullable = false, length = 100)
    private String campaignPictureFive;

    @OneToOne( mappedBy = "campaignCar")
    private Campaign campaign;
}
