package com.example.kwbruunauktion.auktionSystem.service;

import com.example.kwbruunauktion.auktionSystem.dto.BrandColorMixRequest;
import com.example.kwbruunauktion.auktionSystem.dto.BrandColorMixResponse;
import com.example.kwbruunauktion.auktionSystem.dto.SpecificCarModelResponse;
import com.example.kwbruunauktion.auktionSystem.entity.BrandColorMix;
import com.example.kwbruunauktion.auktionSystem.entity.ColorMix;
import com.example.kwbruunauktion.auktionSystem.entity.SpecificCarModel;
import com.example.kwbruunauktion.auktionSystem.repository.BrandColorMixRepository;
import com.example.kwbruunauktion.auktionSystem.repository.ColorMixRepository;
import com.example.kwbruunauktion.auktionSystem.repository.SpecificCarModelRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class BrandColorMixService {

    BrandColorMixRepository brandColorMixRepository;
    SpecificCarModelRepository specificCarModelRepository;
    ColorMixRepository colorMixRepository;


    public BrandColorMixService(BrandColorMixRepository brandColorMixRepository,
                                SpecificCarModelRepository specificCarModelRepository,
                                ColorMixRepository colorMixRepository) {
        this.brandColorMixRepository = brandColorMixRepository;
        this.specificCarModelRepository = specificCarModelRepository;
        this.colorMixRepository = colorMixRepository;
    }

    public BrandColorMixResponse getBrandColorMixById(@PathVariable Long id) {
        return new BrandColorMixResponse(brandColorMixRepository.findById(id).orElseThrow(() -> new RuntimeException("BrandColorMix not found")));
    }

    public List<BrandColorMixResponse> getAllBrandColorMix() {
        List<BrandColorMix> brandColorMixes = brandColorMixRepository.findAll();
        return brandColorMixes.stream().map(brandColorMix -> new BrandColorMixResponse(brandColorMix)).toList();
    }

    public  BrandColorMixResponse addBrandColorMix(BrandColorMixRequest brandColorMixRequest, Long specificCarModelId, Long colorMixId) {
        if(brandColorMixRepository.existsById(brandColorMixRequest.getId())) {
            throw new RuntimeException("BrandColorMix with this ID already exist");
        }
        if(!specificCarModelRepository.existsById(specificCarModelId)) {
            throw new RuntimeException("SpecificCarModel with this ID doesnt exist");
        }
        if(!colorMixRepository.existsById(colorMixId)) {
            throw new RuntimeException("ColorMix with this ID doesnt exist");
        }
        SpecificCarModel existingSpecificCarModel = specificCarModelRepository.findSpecificCarModelById(specificCarModelId);
        ColorMix existingColorMix = colorMixRepository.findColorMixById(colorMixId);
        BrandColorMix createdBrandColorMix = BrandColorMix.builder()
                .specificCarModel(existingSpecificCarModel)
                .colorMix(existingColorMix)
                .build();

        brandColorMixRepository.save(createdBrandColorMix);
        return new BrandColorMixResponse(createdBrandColorMix);
    }

    public void editBrandColorMix(BrandColorMixRequest brandColorMixRequest, Long id) {

    }

    public void deleteBrandColorMix(@PathVariable Long id) {

    }
}
