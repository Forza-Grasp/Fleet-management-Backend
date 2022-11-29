package com.example.kwbruunauktion.auktionSystem.api;

import com.example.kwbruunauktion.auktionSystem.dto.SpecificCarModelRequest;
import com.example.kwbruunauktion.auktionSystem.dto.SpecificCarModelResponse;
import com.example.kwbruunauktion.auktionSystem.service.SpecificCarModelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/specificcarmodel")
public class SpecificCarModelController {

    private final SpecificCarModelService specificCarModelService;

    public SpecificCarModelController(SpecificCarModelService specificCarModelService) {this.specificCarModelService = specificCarModelService;}

    @GetMapping(path = "/{id}")
    SpecificCarModelResponse getSpecificCarModelById(@PathVariable Long id) {
        return specificCarModelService.getSpecificCarModelById(id);
    }

    @GetMapping("/all")
    List<SpecificCarModelResponse> getAllSpecificCarModels() {return specificCarModelService.getSpecificCarModels();}

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    SpecificCarModelResponse addSpecificCarModel(@RequestBody SpecificCarModelRequest body) {
        return specificCarModelService.addSpecificCarModel(body);
    }

    @PutMapping(path = "/{id}")
    ResponseEntity<Boolean> editSpecificCarModel(@RequestBody SpecificCarModelRequest specificCarModelRequest, @PathVariable Long id){
        specificCarModelService.editSpecificCarModel(specificCarModelRequest, id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<Boolean> deleteSpecificCarModel(@PathVariable Long id) {
        specificCarModelService.deleteSpecificCarModel(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }


}
