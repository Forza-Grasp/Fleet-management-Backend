package com.example.kwbruunauktion.auktionSystem.dto;

import com.example.kwbruunauktion.auktionSystem.entity.ColorMix;
import com.example.kwbruunauktion.auktionSystem.entity.ColorTypes;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ColorTypesRequest {
    private String type;

    public static ColorTypes getColorTypesEntity(ColorTypesRequest colorTypesRequest){
        return ColorTypes.builder()
                .type(colorTypesRequest.type)
                .build();
    }

    public ColorTypesRequest(ColorTypes colorType) {
        this.type = colorType.getType();
    }
}
