package com.example.kwbruunauktion.auktionSystem.api.damageMatrix;

import com.example.kwbruunauktion.auktionSystem.dto.damageMatrix.request.DamageMatrixRequest;
import com.example.kwbruunauktion.auktionSystem.dto.damageMatrix.response.DamageMatrixResponse;
import com.example.kwbruunauktion.auktionSystem.entity.damageMatrix.DamageMatrix;
import com.example.kwbruunauktion.auktionSystem.service.damageMatrix.DamageMatrixService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

  @GetMapping("/{id}")
  public DamageMatrixResponse getDamageMatrixById(@PathVariable Long id){
    return damageMatrixService.getDamageMatrixById(id);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public void addDamageMatrix(@RequestBody DamageMatrixRequest damageMatrixRequest){
    damageMatrixService.addDamageMatrix(damageMatrixRequest);
  }

  @DeleteMapping("/{id}")
  public void deleteDamageMatrix(@PathVariable Long id){
    damageMatrixService.deleteDamageMatrix(id);
  }

  @PutMapping
  public void updateDamageMatrix(@RequestBody DamageMatrixRequest damageMatrixRequest){
    damageMatrixService.updateDamageMatrix(damageMatrixRequest);
  }
}
