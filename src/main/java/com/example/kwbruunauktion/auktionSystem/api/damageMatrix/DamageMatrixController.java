package com.example.kwbruunauktion.auktionSystem.api.damageMatrix;

import com.example.kwbruunauktion.auktionSystem.dto.damageMatrix.response.DamageMatrixResponse;
import com.example.kwbruunauktion.auktionSystem.entity.damageMatrix.DamageMatrix;
import com.example.kwbruunauktion.auktionSystem.service.damageMatrix.DamageMatrixService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/damageMatrix")
public class DamageMatrixController {

  DamageMatrixService damageMatrixService;

  public DamageMatrixController(DamageMatrixService damageMatrixService) {
    this.damageMatrixService = damageMatrixService;
  }

  @GetMapping
  public List<DamageMatrixResponse> getAllDamageMatrix(Pageable p) {
    return damageMatrixService.getAllDamageMatrix(p);
  }
  @GetMapping("/all")
  public List<DamageMatrixResponse> getAllDamageMatrix(){
    return damageMatrixService.getAllDamageMatrix();
  }
}
