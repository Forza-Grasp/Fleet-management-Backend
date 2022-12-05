package com.example.kwbruunauktion.auktionSystem.service.damageMatrix;


import com.example.kwbruunauktion.auktionSystem.dto.damageMatrix.request.SpecificDamageRequest;
import com.example.kwbruunauktion.auktionSystem.dto.damageMatrix.response.SpecificDamageResponse;
import com.example.kwbruunauktion.auktionSystem.entity.damageMatrix.DamageMatrix;
import com.example.kwbruunauktion.auktionSystem.entity.damageMatrix.SpecificDamage;
import com.example.kwbruunauktion.auktionSystem.repository.damageMatrix.DamageMatrixRepository;
import com.example.kwbruunauktion.auktionSystem.repository.damageMatrix.SpecificDamageRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SpecificDamageService {


  private final SpecificDamageRepository specificDamageRepository;
  private final DamageMatrixRepository damageMatrixRepository;

  public SpecificDamageService(SpecificDamageRepository specificDamageRepository,
                               DamageMatrixRepository damageMatrixRepository) {
    this.specificDamageRepository = specificDamageRepository;
    this.damageMatrixRepository = damageMatrixRepository;
  }

  public List<SpecificDamageResponse> getAllSpecificDamages() {
    List<SpecificDamage> specificDamages = specificDamageRepository.findAll();
    return specificDamages.stream().map(SpecificDamageResponse::new).toList();
  }

  public List<SpecificDamageResponse> getAllSpecificDamages(Pageable p) {
    Page<SpecificDamage> specificDamages = specificDamageRepository.findAll(p);
    return specificDamages.stream().map(SpecificDamageResponse::new).toList();
  }

  public SpecificDamageResponse getSpecificDamageById(@PathVariable long id) {
    SpecificDamage specificDamage = specificDamageRepository.findById(id).orElseThrow();
    return new SpecificDamageResponse(specificDamage);
  }

  public List<SpecificDamageResponse> getAllSpecificDamagesByDamageMatrixId(@PathVariable long id) {
    List<SpecificDamage> specificDamages = specificDamageRepository.findAllByDamageMatrixId(id);
    return specificDamages.stream().map(SpecificDamageResponse::new).toList();
  }

  public SpecificDamageResponse addSpecificDamage(SpecificDamageRequest specificDamageRequest) {
    if (!damageMatrixRepository.existsById(specificDamageRequest.getMatrixId())){
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "DamageMatrix does not exist");
    }
    DamageMatrix damageMatrix = damageMatrixRepository.findById(specificDamageRequest.getMatrixId()).orElseThrow();
    SpecificDamage specificDamage = SpecificDamage.builder()
        .damage(specificDamageRequest.getDamage())
        .price(specificDamageRequest.getPrice())
        .damageMatrix(damageMatrix)
        .build();
    specificDamageRepository.save(specificDamage);
    return new SpecificDamageResponse(specificDamage);
  }

  public void deleteSpecificDamage(@PathVariable long id) {
    if (!specificDamageRepository.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ("SpecificDamage does not exist"));
    }
    specificDamageRepository.deleteById(id);
  }

  public void updateSpecificDamage(SpecificDamageRequest specificDamageRequest) {
    if (!specificDamageRepository.existsById(specificDamageRequest.getId())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ("SpecificDamage does not exist"));
    }
    if (!damageMatrixRepository.existsById(specificDamageRequest.getMatrixId())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ("DamageMatrix does not exist"));
    }
    SpecificDamage specificDamage = specificDamageRepository.findById(specificDamageRequest.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Specific Damage not found"));
    if (specificDamageRequest.getDamage() != null) {
      specificDamage.setDamage(specificDamageRequest.getDamage());
    }
    if (specificDamageRequest.getPrice() != 0 || specificDamageRequest.getPrice() < 0) {
      specificDamage.setPrice(specificDamageRequest.getPrice());
    }
    specificDamageRepository.save(specificDamage);
  }


}
