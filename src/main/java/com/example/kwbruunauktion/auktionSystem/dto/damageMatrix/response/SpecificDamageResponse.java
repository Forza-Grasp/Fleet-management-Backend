package com.example.kwbruunauktion.auktionSystem.dto.damageMatrix.response;

import com.example.kwbruunauktion.auktionSystem.entity.damageMatrix.DamageMatrix;
import com.example.kwbruunauktion.auktionSystem.entity.damageMatrix.SpecificDamage;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SpecificDamageResponse {

  private Long id;

  private String damages;

  private double price;

  private Long matrixId;

  private DamageMatrix damageMatrix;

  public SpecificDamageResponse(SpecificDamage s) {
    this.id = s.getId();
    this.damages = s.getDamage();
    this.price = s.getPrice();
    this.matrixId = s.getDamageMatrix().getId();
  }
}
