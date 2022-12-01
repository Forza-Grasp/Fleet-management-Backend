package com.example.kwbruunauktion.auktionSystem.api;

import com.example.kwbruunauktion.auktionSystem.dto.BrandColorMixRequest;
import com.example.kwbruunauktion.auktionSystem.dto.BrandColorMixResponse;
import com.example.kwbruunauktion.auktionSystem.service.BrandColorMixService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/brand-color-mix")
public class BrandColorMixController {

    private final BrandColorMixService brandColorMixService;

    public BrandColorMixController(BrandColorMixService brandColorMixService) {this.brandColorMixService = brandColorMixService;}

    @GetMapping(path = "/{id}")
    BrandColorMixResponse getBrandColorMixById(@PathVariable Long id) {
        return brandColorMixService.getBrandColorMixById(id);
    }

    @GetMapping("/all")
    List<BrandColorMixResponse> getAllBrandColorMixs() {return brandColorMixService.getAllBrandColorMix();}

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    BrandColorMixResponse addBrandColorMix(@RequestBody BrandColorMixRequest body) {
        return brandColorMixService.addBrandColorMix(body);
    }

    @PutMapping()
    ResponseEntity<Boolean> editBrandColorMix(@RequestBody BrandColorMixRequest brandColorMixRequest) {
        brandColorMixService.editBrandColorMix(brandColorMixRequest);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<Boolean> deleteBrandColorMix(@PathVariable Long id) {
        brandColorMixService.deleteBrandColorMix(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

}
