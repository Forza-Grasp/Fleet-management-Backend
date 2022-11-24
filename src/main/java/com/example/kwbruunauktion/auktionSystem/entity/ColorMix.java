package com.example.kwbruunauktion.auktionSystem.entity;


import com.example.kwbruunauktion.auktionSystem.enums.ColorTypes;
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
  @Column(name = "id")
  private Long id;

  @Column(length = 30, nullable = false)
  private String colorCode;

  @Enumerated(EnumType.STRING)
  @Column(length = 30, nullable = false)
  private ColorTypes colorType;

  @Column(length = 30, nullable = false)
  private String colorName;

  @OneToOne(mappedBy = "colorMix")
  private BrandColorMix brandColorMix;
}
