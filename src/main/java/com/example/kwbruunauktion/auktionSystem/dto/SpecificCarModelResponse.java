package com.example.kwbruunauktion.auktionSystem.dto;


import com.example.kwbruunauktion.auktionSystem.entity.SpecificCarModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

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

    public SpecificCarModelResponse (SpecificCarModel specificCarModel) {
        this.brand = specificCarModel.getBrand();
        this.model = specificCarModel.getModel();
        this.modelYear = specificCarModel.getModelYear();
    }

}