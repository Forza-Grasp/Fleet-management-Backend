package com.example.kwbruunauktion.auktionSystem.dto.damageMatrix.request;

import com.example.kwbruunauktion.auktionSystem.entity.damageMatrix.DamageMatrix;
import com.example.kwbruunauktion.auktionSystem.entity.damageMatrix.SpecificDamage;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SpecificDamageRequest {

  private Long id;

  private String damage;

  private double price;

  private DamageMatrix damageMatrix;

  public static SpecificDamage getSpecificDamageEntity(SpecificDamageRequest s) {
    return SpecificDamage.builder()
        .id(s.getId())
        .damage(s.getDamage())
        .price(s.getPrice())
        .damageMatrix(s.getDamageMatrix())
        .build();
  }

  public SpecificDamageRequest(SpecificDamage specificDamage) {
    this.id = specificDamage.getId();
    this.damage = specificDamage.getDamage();
    this.price = specificDamage.getPrice();
    this.damageMatrix = specificDamage.getDamageMatrix();
  }

}
