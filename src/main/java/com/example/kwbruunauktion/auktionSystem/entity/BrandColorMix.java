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
public class BrandColorMix {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(cascade = CascadeType.ALL)
  private SpecificCarModel specificCarModel;

  @OneToOne(cascade = CascadeType.ALL)
  private ColorMix colorMix;

  @Column(length = 100, nullable = false)
  private double price;
}
