package com.example.kwbruunauktion.auktionSystem.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class SpecificCarModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 30, nullable = false)
  private String brand;

  @Column(length = 50, nullable = false)
  private String model;

  @Column(length = 30, nullable = false)
  private String modelYear;

  @ManyToOne
  private Member member;

  @OneToOne(mappedBy = "specificCarModel")
  private BrandColorMix brandColorMix;
}
