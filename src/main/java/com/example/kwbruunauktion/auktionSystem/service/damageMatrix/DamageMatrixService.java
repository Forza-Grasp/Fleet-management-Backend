package com.example.kwbruunauktion.auktionSystem.service.damageMatrix;


import com.example.kwbruunauktion.auktionSystem.dto.damageMatrix.request.DamageMatrixRequest;
import com.example.kwbruunauktion.auktionSystem.dto.damageMatrix.response.DamageMatrixResponse;
import com.example.kwbruunauktion.auktionSystem.entity.damageMatrix.DamageMatrix;
import com.example.kwbruunauktion.auktionSystem.entity.users.UserBuyer;
import com.example.kwbruunauktion.auktionSystem.repository.damageMatrix.DamageMatrixRepository;
import com.example.kwbruunauktion.auktionSystem.repository.users.UserBuyerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DamageMatrixService {

  private final DamageMatrixRepository damageMatrixRepository;
  private final UserBuyerRepository userBuyerRepository;

  public DamageMatrixService(DamageMatrixRepository damageMatrixRepository,
                             UserBuyerRepository userBuyerRepository) {
    this.damageMatrixRepository = damageMatrixRepository;
    this.userBuyerRepository = userBuyerRepository;
  }

  public List<DamageMatrixResponse> getAllDamageMatrix() {
    List<DamageMatrix> damageMatrixList = damageMatrixRepository.findAll();
    return damageMatrixList.stream().map(DamageMatrixResponse::new).toList();
  }

  public List<DamageMatrixResponse> getAllDamageMatrix(Pageable p) {
    Page<DamageMatrix> damageMatrixList = damageMatrixRepository.findAll(p);
    return damageMatrixList.stream().map(DamageMatrixResponse::new).toList();
  }

  public DamageMatrixResponse getDamageMatrixById(Long id) {
    DamageMatrix foundDamageMatrix = damageMatrixRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "DamageMatrix not found"));
    return new DamageMatrixResponse(foundDamageMatrix);
  }

  public DamageMatrixResponse addDamageMatrix(DamageMatrixRequest damageMatrixRequest) {
      UserBuyer userBuyer = userBuyerRepository.findById(damageMatrixRequest.getUserId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    System.out.println(damageMatrixRequest);
      DamageMatrix newDamageMatrix = DamageMatrix.builder()
              .userWithRoles(userBuyer)
              .valuta(damageMatrixRequest.getValuta())
              .build();
      newDamageMatrix = damageMatrixRepository.save(newDamageMatrix);
      return new DamageMatrixResponse(newDamageMatrix);
  }

  public void deleteDamageMatrix(@PathVariable Long id) {
    if (!damageMatrixRepository.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "DamageMatrix not found");
    }
    damageMatrixRepository.deleteById(id);
  }

  public void updateDamageMatrix(DamageMatrixRequest damageMatrixRequest) {
    if (!damageMatrixRepository.existsById(damageMatrixRequest.getMatrixId())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "DamageMatrix does not exist");
    }
    DamageMatrix foundDamageMatrix = damageMatrixRepository.findById(damageMatrixRequest.getMatrixId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "DamageMatrix not found"));
    if (damageMatrixRequest.getValuta() != null) {
      foundDamageMatrix.setValuta(damageMatrixRequest.getValuta());
    }
    damageMatrixRepository.save(foundDamageMatrix);
  }

  public List<DamageMatrixResponse> getAllDamageMatrixByUserId(Long id) {
    List<DamageMatrix> damageMatrixList = damageMatrixRepository.findAllByUserWithRolesId(id);
    return damageMatrixList.stream().map(DamageMatrixResponse::new).toList();
  }


}
