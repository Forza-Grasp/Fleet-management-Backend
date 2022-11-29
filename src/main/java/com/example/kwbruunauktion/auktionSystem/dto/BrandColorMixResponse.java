package com.example.kwbruunauktion.auktionSystem.dto;

import com.example.kwbruunauktion.auktionSystem.entity.BrandColorMix;
import com.example.kwbruunauktion.auktionSystem.entity.ColorMix;
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
public class BrandColorMixResponse {

    private Long id;

    private SpecificCarModel specificCarModel;

    private ColorMix colorMix;

    private LocalDateTime created;

    private LocalDateTime updated;


    public BrandColorMixResponse(BrandColorMix brandColorMix) {
        this.id = brandColorMix.getId();
        this.specificCarModel = brandColorMix.getSpecificCarModel();
        this.colorMix = brandColorMix.getColorMix();
        this.created = brandColorMix.getCreated();
        this.updated = brandColorMix.getUpdated();
    }

}
