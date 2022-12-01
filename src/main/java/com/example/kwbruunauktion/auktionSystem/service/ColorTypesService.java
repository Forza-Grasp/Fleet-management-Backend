package com.example.kwbruunauktion.auktionSystem.service;

import com.example.kwbruunauktion.auktionSystem.dto.ColorTypesRequest;
import com.example.kwbruunauktion.auktionSystem.dto.ColorTypesResponse;
import com.example.kwbruunauktion.auktionSystem.entity.ColorTypes;
import com.example.kwbruunauktion.auktionSystem.repository.ColorTypesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
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
        List<ColorTypes> allColorTypes = colorTypesRepository.findAll();
        return allColorTypes.stream().map(colorTypes -> new ColorTypesResponse(colorTypes)).toList();
    }

    public ColorTypesResponse getColorTypeById(Long id){
        return new ColorTypesResponse(
                colorTypesRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Color type with id: "+id+" could not be found"))
        );
    }

    public ColorTypesResponse editColorType(ColorTypesRequest colorTypesRequest){
        ColorTypes foundColorType = colorTypesRepository.
                findById(colorTypesRequest.getId()).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Color type with id: " + colorTypesRequest.getId() + " could not be found"));

        if (colorTypesRequest.getType() != null) {
            foundColorType.setType(colorTypesRequest.getType());
        }
        foundColorType.setUpdated(LocalDateTime.now());
        colorTypesRepository.save(foundColorType);
        return new ColorTypesResponse(foundColorType);
    }

    @Transactional
    public ColorTypesResponse deleteColorTypeById(Long id){
        ColorTypes foundColorType = colorTypesRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Color type with id: "+id+" could not be found"));

        colorTypesRepository.deleteById(id);
        return new ColorTypesResponse(foundColorType);
    }
}
