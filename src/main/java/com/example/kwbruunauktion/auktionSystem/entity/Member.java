package com.example.kwbruunauktion.auktionSystem.entity;

import com.example.kwbruunauktion.security.entity.UserWithRoles;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Member extends UserWithRoles {

  @Column(length = 30, nullable = false)
  private String firstName;

  @Column(length = 30, nullable = false)
  private String lastName;

  @Column(length = 30, nullable = false)
  private String phoneNumber;

  @Column(length = 30, nullable = false)
  private String companyName;

  @Column(length = 30, nullable = false)
  private String companyEuVatNumber;

  @Column(length = 30, nullable = false)
  private String addressLine1;

  @Column(length = 30)
  private String addressLine2;

  @Column(length = 30, nullable = false)
  private String city;

  @Column(length = 30, nullable = false)
  private String zipCode;

  @ManyToMany(cascade = CascadeType.ALL)
  @ToString.Exclude
  List<SpecificCarModel> ViewableCarBrands;

  @OneToOne(mappedBy = "member")
  private DamageMatrix damageMatrix;
}
