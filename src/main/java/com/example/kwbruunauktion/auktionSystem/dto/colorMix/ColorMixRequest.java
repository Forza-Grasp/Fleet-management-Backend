package com.example.kwbruunauktion.auktionSystem.dto.colorMix;

import com.example.kwbruunauktion.auktionSystem.entity.ColorMix;
import com.example.kwbruunauktion.auktionSystem.entity.ColorTypes;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ColorMixRequest {
    private Long id;
    private String colorCode;
    private Long colorTypeId;
    private String colorName;


    public static ColorMix getColorMixEntity(ColorMixRequest colorMix) {
        return ColorMix.builder()
                .colorCode(colorMix.getColorCode())
                .colorName(colorMix.getColorName())
                .colorType(ColorTypes.builder().id(colorMix.getColorTypeId()).build())
                .build();


    }

}
