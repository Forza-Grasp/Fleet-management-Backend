package com.example.kwbruunauktion.auktionSystem.service.damageMatrix;


import com.example.kwbruunauktion.auktionSystem.repository.damageMatrix.DamageMatrixRepository;
import com.example.kwbruunauktion.auktionSystem.repository.damageMatrix.SpecificDamageRepository;
import org.springframework.stereotype.Service;

@Service
public class SpecificDamageMatrix {


  private final SpecificDamageRepository specificDamageRepository;
  private final DamageMatrixRepository damageMatrixRepository;

  public SpecificDamageMatrix(SpecificDamageRepository specificDamageRepository,
                              DamageMatrixRepository damageMatrixRepository) {
    this.specificDamageRepository = specificDamageRepository;
    this.damageMatrixRepository = damageMatrixRepository;
  }


}
