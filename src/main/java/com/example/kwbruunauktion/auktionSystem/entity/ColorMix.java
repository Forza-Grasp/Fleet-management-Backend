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
public class ColorMix {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 100, nullable = false)
  private String colorCode;

  @OneToOne(cascade = CascadeType.ALL)
  private ColorTypes colorType;

  @Column(length = 100, nullable = false)
  private String colorName;

  @OneToOne(mappedBy = "colorMix")
  private BrandColorMix brandColorMix;
}
