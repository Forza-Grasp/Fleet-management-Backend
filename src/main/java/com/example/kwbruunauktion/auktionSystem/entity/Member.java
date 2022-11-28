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

  @Column(length = 100, nullable = false)
  private String firstName;

  @Column(length = 100, nullable = false)
  private String lastName;

  @Column(length = 100, nullable = false)
  private String phoneNumber;

  @Column(length = 100, nullable = false)
  private String companyName;

  @Column(length = 100, nullable = false)
  private String companyEuVatNumber;

  @Column(length = 100, nullable = false)
  private String addressLine1;

  @Column(length = 100)
  private String addressLine2;

  @Column(length = 100, nullable = false)
  private String city;

  @Column(length = 100, nullable = false)
  private String zipCode;

  @JoinTable(name = "viewableCarBrands")
  @ManyToMany(cascade = CascadeType.ALL)
  @ToString.Exclude
  List<SpecificCarModel> viewableCarBrands;


  @OneToOne(mappedBy = "member")
  private CampaignBid campaignBid;

  @OneToOne(mappedBy = "member")
  private DamageMatrix damageMatrix;

  @Column(length = 100, nullable = false)
  private Long ownershipId;

}
