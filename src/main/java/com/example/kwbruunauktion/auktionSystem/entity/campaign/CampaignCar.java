package com.example.kwbruunauktion.auktionSystem.entity.campaign;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
  private Long id;

  @Column(nullable = false, length = 100)
  private String brand;

  @Column(nullable = false, length = 100)
  private String model;

  @Column(nullable = false, length = 300)
  private String modelText;

  @Column(nullable = false, length = 100)
  private String description;

  @Column(nullable = false, length = 100)
  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate exceptedRegistrationFromDate;

  @Column(nullable = false, length = 100)
  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate exceptedRegistrationToDate;

  @Column(nullable = false, length = 10)
  private int monthsRegistered;

  @Column(nullable = false, length = 100)
  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate earliestExceptedReturnDate;

  @Column(nullable = false, length = 100)
  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate latestExceptedReturnDate;

  @Column(nullable = false, length = 100)
  private String mileage;

  @Column(nullable = false, length = 100)
  private String depositPerCar;

  @Column(nullable = false, length = 100)
  private String damageAndMileage;

  @Column(nullable = false, length = 100)
  private String supplyingConditions;

  @Column(nullable = false, length = 300)
  private String campaignPictureOne;

  @OneToOne(mappedBy = "campaignCar")
  private Campaign campaign;

  @CreationTimestamp
  private LocalDateTime created;

  @UpdateTimestamp
  private LocalDateTime updated;
}
