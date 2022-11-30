package com.example.kwbruunauktion.auktionSystem.dto;

import com.example.kwbruunauktion.auktionSystem.entity.SpecificCarModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SpecificCarModelRequest {

    private Long id;

    private String brand;

    private String model;

    private String modelYear;


    public SpecificCarModelRequest(SpecificCarModel specificCarModel) {
        this.brand = specificCarModel.getBrand();
        this.model = specificCarModel.getModel();
        this.modelYear = specificCarModel.getModelYear();
    }

}
