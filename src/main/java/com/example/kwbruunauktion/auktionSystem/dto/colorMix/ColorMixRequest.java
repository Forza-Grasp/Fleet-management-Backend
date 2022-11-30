package com.example.kwbruunauktion.auktionSystem.dto.colorMix;

import com.example.kwbruunauktion.auktionSystem.entity.ColorMix;
import com.example.kwbruunauktion.auktionSystem.entity.ColorTypes;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ColorMixRequest {
    private Long id;
    private String colorCode;
    private Long colorTypeId;
    private String colorName;


    public static ColorMix getColorMixEntity(ColorMixRequest colorMix) {
        return ColorMix.builder()
                .id(colorMix.getId())
                .colorCode(colorMix.getColorCode())
                .colorName(colorMix.getColorName())
                .colorType(ColorTypes.builder().id(colorMix.getColorTypeId()).build())
                .build();

    }

    public ColorMixRequest(ColorMix colorMix){
        this.id = colorMix.getId();
        this.colorCode = colorMix.getColorCode();
        this.colorTypeId = colorMix.getColorType().getId();
        this.colorName = colorMix.getColorName();
    }

}
