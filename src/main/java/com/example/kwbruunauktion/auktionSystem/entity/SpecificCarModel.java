package com.example.kwbruunauktion.auktionSystem.entity;

import com.example.kwbruunauktion.auktionSystem.entity.users.UserBuyer;
import com.example.kwbruunauktion.auktionSystem.entity.users.UserLeaser;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class SpecificCarModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 100, nullable = false)
  private String brand;

  @Column(length = 100, nullable = false)
  private String model;

  @Column(length = 100, nullable = false)
  private String modelYear;

  @CreationTimestamp
  private LocalDateTime created;

  @UpdateTimestamp
  private LocalDateTime updated;

  @ManyToMany(mappedBy = "viewableCarBrands")
  @ToString.Exclude
  private List<UserBuyer> userBuyer;

  @ManyToMany(mappedBy = "viewableCarBrands")
  @ToString.Exclude
  private List<UserLeaser> userLeaser;

  @OneToMany(mappedBy = "specificCarModel")
  private List<BrandColorMix> brandColorMix;

}
