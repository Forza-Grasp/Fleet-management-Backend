package com.example.kwbruunauktion.auktionSystem.dto.colorMix;

import com.example.kwbruunauktion.auktionSystem.entity.ColorMix;
import com.example.kwbruunauktion.auktionSystem.repository.ColorMixRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ColorMixResponse {

    private Long id;
    private String colorCode;
    private Long colorTypeId;
    private String colorName;

    public ColorMixResponse(ColorMix colorMix){
        this.id = colorMix.getId();
        this.colorCode = colorMix.getColorCode();
        this.colorTypeId = colorMix.getColorType().getId();
        this.colorName = colorMix.getColorName();
    }

}
