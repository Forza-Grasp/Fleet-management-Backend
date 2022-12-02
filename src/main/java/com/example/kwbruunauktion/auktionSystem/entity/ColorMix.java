package com.example.kwbruunauktion.auktionSystem.entity;


import com.example.kwbruunauktion.auktionSystem.dto.colorMix.ColorMixRequest;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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

  @ManyToOne
  private ColorTypes colorType;

  @Column(length = 100, nullable = false)
  private String colorName;

  @OneToMany(mappedBy = "colorMix")
  private List<BrandColorMix> brandColorMix;

  @CreationTimestamp
  private LocalDateTime created;

  @UpdateTimestamp
  private LocalDateTime updated;


  public ColorMix(String colorCode, String colorName, ColorTypes colorType){
    this.colorCode = colorCode;
    this.colorName = colorName;
    this.colorType = colorType;

  }

}
