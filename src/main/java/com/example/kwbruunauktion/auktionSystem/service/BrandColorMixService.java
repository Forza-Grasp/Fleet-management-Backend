package com.example.kwbruunauktion.auktionSystem.service;

import com.example.kwbruunauktion.auktionSystem.dto.BrandColorMixRequest;
import com.example.kwbruunauktion.auktionSystem.dto.BrandColorMixResponse;
import com.example.kwbruunauktion.auktionSystem.dto.SpecificCarModelRequest;
import com.example.kwbruunauktion.auktionSystem.dto.SpecificCarModelResponse;
import com.example.kwbruunauktion.auktionSystem.dto.colorMix.ColorMixRequest;
import com.example.kwbruunauktion.auktionSystem.dto.colorMix.ColorMixResponse;
import com.example.kwbruunauktion.auktionSystem.entity.BrandColorMix;
import com.example.kwbruunauktion.auktionSystem.entity.ColorMix;
import com.example.kwbruunauktion.auktionSystem.entity.ColorTypes;
import com.example.kwbruunauktion.auktionSystem.entity.SpecificCarModel;
import com.example.kwbruunauktion.auktionSystem.repository.BrandColorMixRepository;
import com.example.kwbruunauktion.auktionSystem.repository.ColorMixRepository;
import com.example.kwbruunauktion.auktionSystem.repository.SpecificCarModelRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

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

    public  BrandColorMixResponse addBrandColorMix(BrandColorMixRequest brandColorMixRequest) {
        if(!specificCarModelRepository.existsById(brandColorMixRequest.getSpecificCarModelId())) {
            throw new RuntimeException("SpecificCarModel with this ID doesnt exist");
        }
        if(!colorMixRepository.existsById(brandColorMixRequest.getColorMixId())) {
            throw new RuntimeException("ColorMix with this ID doesnt exist");
        }
        SpecificCarModel specificCarModel = specificCarModelRepository.findById(brandColorMixRequest.getSpecificCarModelId()).orElseThrow(() -> new RuntimeException("SpecificCarModel not found"));
        ColorMix colorMix = colorMixRepository.findById(brandColorMixRequest.getColorMixId()).orElseThrow(() -> new RuntimeException("ColorMix not found"));
        BrandColorMix newBrandColorMix = BrandColorMix.builder()
                .specificCarModel(specificCarModel)
                .colorMix(colorMix)
                .build();
        System.out.println(brandColorMixRequest);
        brandColorMixRepository.save(newBrandColorMix);
        return new BrandColorMixResponse(newBrandColorMix);
    }




    public void editBrandColorMix(BrandColorMixRequest brandColorMixRequest) {
        BrandColorMix brandColorMix = brandColorMixRepository.findById(brandColorMixRequest.getId()).orElseThrow(() -> new RuntimeException("BrandColorMix with this ID does not exist"));

        if(!specificCarModelRepository.existsById(brandColorMixRequest.getSpecificCarModelId())) {
            throw new RuntimeException("SpecificCarModel with this ID doesnt exist");
        }
        if(!colorMixRepository.existsById(brandColorMixRequest.getColorMixId())) {
            throw new RuntimeException("ColorMix with this ID doesnt exist");
        }

        BrandColorMix tempBrandColorMix = BrandColorMixRequest.getBrandColorMixEntity(brandColorMixRequest);
        brandColorMix.setSpecificCarModel(tempBrandColorMix.getSpecificCarModel());

        brandColorMix.setColorMix(tempBrandColorMix.getColorMix());

        brandColorMixRepository.save(brandColorMix);
    }

    public void deleteBrandColorMix(@PathVariable Long id) {
        BrandColorMix brandColorMix = brandColorMixRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"BrandColorMix with this ID does not exist"));
        brandColorMixRepository.delete(brandColorMix);
    }

}
