package com.example.kwbruunauktion.auktionSystem.service;

import com.example.kwbruunauktion.auktionSystem.dto.SpecificCarModelRequest;
import com.example.kwbruunauktion.auktionSystem.dto.SpecificCarModelResponse;
import com.example.kwbruunauktion.auktionSystem.entity.SpecificCarModel;
import com.example.kwbruunauktion.auktionSystem.repository.SpecificCarModelRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SpecificCarModelService {
    SpecificCarModelRepository specificCarModelRepository;

    public SpecificCarModelService(SpecificCarModelRepository specificCarModelRepository) {
        this.specificCarModelRepository = specificCarModelRepository;
    }

    public SpecificCarModelResponse getSpecificCarModelById(@PathVariable Long id) {
        return new SpecificCarModelResponse(specificCarModelRepository.findById(id).orElseThrow(() -> new RuntimeException("SpecificCarModel not found")));
    }

    public List<SpecificCarModelResponse> getSpecificCarModels() {
        List<SpecificCarModel> specificCarModels = specificCarModelRepository.findAll();
        return specificCarModels.stream().map(specificCarModel -> new SpecificCarModelResponse(specificCarModel)).toList();

    }

    public SpecificCarModelResponse addSpecificCarModel(SpecificCarModelRequest specificCarModelRequest) {
        SpecificCarModel createdSpecificCarModel = SpecificCarModel.builder()
                .brand(specificCarModelRequest.getBrand())
                .model(specificCarModelRequest.getModel())
                .modelYear(specificCarModelRequest.getModelYear())
                .build();

        specificCarModelRepository.save(createdSpecificCarModel);
        return new SpecificCarModelResponse(createdSpecificCarModel);
    }

    public void editSpecificCarModel(SpecificCarModelRequest specificCarModelRequest) {
        SpecificCarModel specificCarModel = specificCarModelRepository.findById(specificCarModelRequest.getId()).orElseThrow(() -> new RuntimeException("SpecificCarModel with this ID does not exist"));

        specificCarModel.setBrand(specificCarModelRequest.getBrand());
        specificCarModel.setModel(specificCarModelRequest.getModel());
        specificCarModel.setModelYear(specificCarModelRequest.getModelYear());
        specificCarModelRepository.save(specificCarModel);
    }

    public void deleteSpecificCarModel(@PathVariable Long id) {
        SpecificCarModel specificCarModel = specificCarModelRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"SpecificCarModel with this ID does not exist"));
        specificCarModelRepository.delete(specificCarModel);
    }
}
