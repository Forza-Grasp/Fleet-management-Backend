package com.example.kwbruunauktion.auktionSystem.service.damageMatrix;


import com.example.kwbruunauktion.auktionSystem.dto.damageMatrix.response.SpecificDamageResponse;
import com.example.kwbruunauktion.auktionSystem.entity.damageMatrix.SpecificDamage;
import com.example.kwbruunauktion.auktionSystem.repository.damageMatrix.DamageMatrixRepository;
import com.example.kwbruunauktion.auktionSystem.repository.damageMatrix.SpecificDamageRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class SpecificDamageMatrix {


  private final SpecificDamageRepository specificDamageRepository;
  private final DamageMatrixRepository damageMatrixRepository;

  public SpecificDamageMatrix(SpecificDamageRepository specificDamageRepository,
                              DamageMatrixRepository damageMatrixRepository) {
    this.specificDamageRepository = specificDamageRepository;
    this.damageMatrixRepository = damageMatrixRepository;
  }

  List<SpecificDamageResponse> getAllSpecificDamages() {
    List<SpecificDamage> specificDamages = specificDamageRepository.findAll();
    return specificDamages.stream().map(SpecificDamageResponse::new).toList();
  }
  List<SpecificDamageResponse> getAllSpecificDamages(Pageable p) {
    Page<SpecificDamage> specificDamages = specificDamageRepository.findAll(p);
    return specificDamages.stream().map(SpecificDamageResponse::new).toList();
  }

  SpecificDamageResponse getSpecificDamageById(@PathVariable long id) {
    SpecificDamage specificDamage = specificDamageRepository.findById(id).orElseThrow();
    return new SpecificDamageResponse(specificDamage);
  }
  List<SpecificDamageResponse> getAllSpecificDamagesByDamageMatrixId(@PathVariable long id) {
    List<SpecificDamage> specificDamages = specificDamageRepository.findAllByDamageMatrixId(id);
    return specificDamages.stream().map(SpecificDamageResponse::new).toList();
  }

}
