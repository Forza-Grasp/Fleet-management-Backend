package com.example.kwbruunauktion.auktionSystem.entity;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

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

  @ManyToOne(cascade = CascadeType.ALL)
  private ColorTypes colorType;

  @Column(length = 100, nullable = false)
  private String colorName;

  @OneToOne(mappedBy = "colorMix")
  private BrandColorMix brandColorMix;

  @CreationTimestamp
  private LocalDateTime created;

  @UpdateTimestamp
  private LocalDateTime updated;
}
