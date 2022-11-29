package com.example.kwbruunauktion.auktionSystem.api.damageMatrix;

import com.example.kwbruunauktion.auktionSystem.dto.damageMatrix.request.SpecificDamageRequest;
import com.example.kwbruunauktion.auktionSystem.dto.damageMatrix.response.SpecificDamageResponse;
import com.example.kwbruunauktion.auktionSystem.service.damageMatrix.SpecificDamageService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/specificDamage")
public class SpecificDamageController {

  SpecificDamageService specificDamageService;

  public SpecificDamageController(SpecificDamageService specificDamageService) {
    this.specificDamageService = specificDamageService;
  }

  @GetMapping
  public List<SpecificDamageResponse> getAllSpecificDamage(Pageable p) {
    return specificDamageService.getAllSpecificDamages(p);
  }

  @GetMapping("/all")
  public List<SpecificDamageResponse> getAllSpecificDamage() {
    return specificDamageService.getAllSpecificDamages();
  }

  @GetMapping("/{id}")
  public SpecificDamageResponse getSpecificDamageById(@PathVariable Long id) {
    return specificDamageService.getSpecificDamageById(id);
  }

  @GetMapping("/damageMatrix/{id}")
  List<SpecificDamageResponse> getAllSpecificDamagesByDamageMatrixId(@PathVariable Long id) {
    return specificDamageService.getAllSpecificDamagesByDamageMatrixId(id);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public void addSpecificDamage(@RequestBody SpecificDamageRequest specificDamageRequest) {
    specificDamageService.addSpecificDamage(specificDamageRequest);
  }

  @DeleteMapping("/{id}")
  public void deleteSpecificDamage(@PathVariable Long id) {
    specificDamageService.deleteSpecificDamage(id);
  }

  @PutMapping
  public void updateSpecificDamage(@RequestBody SpecificDamageRequest specificDamageRequest) {
    specificDamageService.updateSpecificDamage(specificDamageRequest);
  }

}


