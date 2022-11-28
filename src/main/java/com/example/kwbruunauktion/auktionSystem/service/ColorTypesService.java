package com.example.kwbruunauktion.auktionSystem.service;

import com.example.kwbruunauktion.auktionSystem.dto.ColorTypesRequest;
import com.example.kwbruunauktion.auktionSystem.dto.ColorTypesResponse;
import com.example.kwbruunauktion.auktionSystem.entity.ColorTypes;
import com.example.kwbruunauktion.auktionSystem.repository.ColorTypesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

public class ColorTypesService {

    ColorTypesRepository colorTypesRepository;

    public ColorTypesService(ColorTypesRepository colorTypesRepository) {
        this.colorTypesRepository = colorTypesRepository;
    }

    public ColorTypesResponse addColorType(ColorTypesRequest colorTypesRequest){
        ColorTypes newColorType = ColorTypesRequest.getColorTypesEntity(colorTypesRequest);
        colorTypesRepository.save(newColorType);
        return new ColorTypesResponse(newColorType);
    }

    public List<ColorTypesResponse> getAllColorTypes(){
        return colorTypesRepository.findAll().stream().map(colorTypes -> new ColorTypesResponse()).collect(Collectors.toList());
    }

    public ColorTypesResponse deleteColorTypeById(long id){
        ColorTypes foundColorType = colorTypesRepository.
                findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Color type with id: "+id+" could not be found"));

        colorTypesRepository.deleteById(id);
        return new ColorTypesResponse(foundColorType);
    }

    public ColorTypesResponse editColorType(ColorTypesRequest colorTypesRequest, long id){
        ColorTypes foundColorType = colorTypesRepository.
                findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Color type with id: "+id+" could not be found"));

        foundColorType.setType(colorTypesRequest.getType());
        return new ColorTypesResponse(foundColorType);
    }


}
