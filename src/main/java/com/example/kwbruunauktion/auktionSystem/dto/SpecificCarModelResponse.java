package com.example.kwbruunauktion.auktionSystem.dto;


import com.example.kwbruunauktion.auktionSystem.entity.Ownership;
import com.example.kwbruunauktion.auktionSystem.entity.SpecificCarModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SpecificCarModelResponse {

    private Long id;

    private String brand;

    private String model;

    private String modelYear;

    private LocalDateTime created;

    private LocalDateTime updated;

    public SpecificCarModelResponse (SpecificCarModel specificCarModel) {
        this.id = specificCarModel.getId();
        this.brand = specificCarModel.getBrand();
        this.model = specificCarModel.getModel();
        this.modelYear = specificCarModel.getModelYear();
        this.created = specificCarModel.getCreated();
        this.updated = specificCarModel.getUpdated();
    }
}
