package com.example.kwbruunauktion.auktionSystem.dto;

import com.example.kwbruunauktion.auktionSystem.entity.BrandColorMix;
import com.example.kwbruunauktion.auktionSystem.entity.ColorMix;
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
public class BrandColorMixRequest {

    private Long id;

    private SpecificCarModel specificCarModel;

    private ColorMix colorMix;

    public BrandColorMixRequest(BrandColorMix brandColorMix) {
        this.id = brandColorMix.getId();
        this.specificCarModel = brandColorMix.getSpecificCarModel();
        this.colorMix = brandColorMix.getColorMix();
    }



}
