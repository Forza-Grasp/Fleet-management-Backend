package com.example.kwbruunauktion.auktionSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ColorTypes {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 100, nullable = false)
  private String type;

  @OneToMany(mappedBy = "colorType")
  @ToString.Exclude
  private List<ColorMix> colorMixes;

  @CreationTimestamp
  private LocalDateTime created;

  @UpdateTimestamp
  private LocalDateTime updated;

  public ColorTypes(String type, LocalDateTime created, LocalDateTime updated) {
    this.type = type;
    this.created = created;
    this.updated = updated;
  }
}
