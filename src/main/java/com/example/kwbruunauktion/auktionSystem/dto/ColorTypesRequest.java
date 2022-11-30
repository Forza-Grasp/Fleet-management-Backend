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
    private Long id;
    private String type;

    public static ColorTypes getColorTypesEntity(ColorTypesRequest colorTypesRequest){
        return ColorTypes.builder()
                .id(colorTypesRequest.getId())
                .type(colorTypesRequest.type)
                .build();
    }

    public ColorTypesRequest(ColorTypes colorType) {
        this.id = colorType.getId();
        this.type = colorType.getType();
    }
}
