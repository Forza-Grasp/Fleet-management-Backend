package com.example.kwbruunauktion.auktionSystem.service;

import com.example.kwbruunauktion.auktionSystem.dto.colorMix.ColorMixRequest;
import com.example.kwbruunauktion.auktionSystem.dto.colorMix.ColorMixResponse;
import com.example.kwbruunauktion.auktionSystem.entity.ColorMix;
import com.example.kwbruunauktion.auktionSystem.repository.ColorMixRepository;
import com.example.kwbruunauktion.auktionSystem.repository.ColorTypesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.plaf.synth.ColorType;
import java.awt.*;
import java.util.List;

@Service
public class ColorMixService {


    ColorMixRepository colorMixRepository;
    ColorTypesRepository colorTypesRepository;
    public ColorMixService(ColorMixRepository colorMixRepository, ColorTypesRepository colorTypesRepository){
        this.colorMixRepository = colorMixRepository;
        this.colorTypesRepository = colorTypesRepository;

    }

    public List<ColorMixResponse> getAll() {
        List<ColorMix> colorMixes = colorMixRepository.findAll();
        return colorMixes.stream().map(colorMix -> new ColorMixResponse(colorMix)).toList();
    }

    public ColorMixResponse getSpecificById(Long id){
         ColorMix colorMix = colorMixRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ColorMix Not Found"));
        return new ColorMixResponse(colorMix);
    }

    public ColorMixResponse addColorMix(ColorMixRequest colorMix) {
        ColorMix tempColorMix = ColorMixRequest.getColorMixEntity(colorMix);
        colorMixRepository.save(tempColorMix);
        return new ColorMixResponse(tempColorMix);
    }

    public void editColorMix(ColorMixRequest color) {
        ColorMix foundColorMix = colorMixRepository.findById(color.getId()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "ColorMix not found"));
        if(color.getColorCode() != null) {
            foundColorMix.setColorCode(color.getColorCode());
        }
        if(color.getColorName() != null){
            foundColorMix.setColorName(color.getColorName());
        }
        if(color.getColorTypeId() != null){
            foundColorMix.setColorType(colorTypesRepository.getReferenceById(color.getColorTypeId()));
        }
        colorMixRepository.save(foundColorMix);
    }


    public void deleteColorMix(Long id) {
        colorMixRepository.deleteById(id);
    }


}
