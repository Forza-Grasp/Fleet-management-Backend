package com.example.kwbruunauktion.auktionSystem.dto.damageMatrix.response;

import com.example.kwbruunauktion.auktionSystem.entity.damageMatrix.DamageMatrix;
import com.example.kwbruunauktion.security.entity.UserWithRoles;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DamageMatrixResponse {

  private Long id;

  private Long userWithRolesId;

  private String valuta;

  private List<SpecificDamageResponse> specificDamages;

  @CreationTimestamp
  private LocalDateTime created;

  @UpdateTimestamp
  private LocalDateTime updated;

  public DamageMatrixResponse(DamageMatrix d){
    this.id = d.getId();
    this.userWithRolesId = d.getUserWithRoles().getId();
    if (d.getSpecificDamage() != null){
      this.specificDamages = d.getSpecificDamage().stream().map(SpecificDamageResponse::new).collect(Collectors.toList());
    }
    this.valuta = d.getValuta();
    this.created = d.getCreated();
    this.updated = d.getUpdated();
  }
}
