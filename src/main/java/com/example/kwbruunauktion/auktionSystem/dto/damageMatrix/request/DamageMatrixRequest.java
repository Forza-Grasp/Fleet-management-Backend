package com.example.kwbruunauktion.auktionSystem.dto.damageMatrix.request;


import com.example.kwbruunauktion.auktionSystem.entity.damageMatrix.DamageMatrix;
import com.example.kwbruunauktion.security.entity.UserWithRoles;
import com.example.kwbruunauktion.security.repository.UserWithRolesRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DamageMatrixRequest {

  private Long matrixId;

  private String valuta;

  private Long userId;

  private UserWithRoles userWithRoles;

  @CreationTimestamp
  private LocalDateTime created;

  @UpdateTimestamp
  private LocalDateTime updated;

  public static DamageMatrix getDamageMatrixEntity(DamageMatrixRequest d){
    return DamageMatrix.builder()
        .valuta(d.getValuta())
        .userWithRoles(d.getUserWithRoles())
        .created(d.getCreated())
        .updated(d.getUpdated())
        .build();
  }

  public DamageMatrixRequest(DamageMatrix damageMatrix) {
    this.matrixId = damageMatrix.getId();
    this.valuta = damageMatrix.getValuta();
    this.created = damageMatrix.getCreated();
    this.updated = damageMatrix.getUpdated();
    this.userId = damageMatrix.getUserWithRoles().getId();
  }

}
