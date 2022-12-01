package com.example.kwbruunauktion.auktionSystem.dto;

import com.example.kwbruunauktion.auktionSystem.dto.colorMix.ColorMixRequest;
import com.example.kwbruunauktion.auktionSystem.entity.BrandColorMix;
import com.example.kwbruunauktion.auktionSystem.entity.ColorMix;
import com.example.kwbruunauktion.auktionSystem.entity.ColorTypes;
import com.example.kwbruunauktion.auktionSystem.entity.SpecificCarModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BrandColorMixRequest {

    private Long id;

    private Long specificCarModelId;

    private Long colorMixId;

    public static BrandColorMix getBrandColorMixEntity(BrandColorMixRequest brandColorMixRequest){
        return BrandColorMix.builder()
                .id(brandColorMixRequest.getColorMixId())
                .colorMix(ColorMix.builder().id(brandColorMixRequest.getColorMixId()).build())
                .specificCarModel(SpecificCarModel.builder().id(brandColorMixRequest.getSpecificCarModelId()).build())
                .build();
    }












}
