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
    private LocalDateTime created;
    private LocalDateTime updated;

    public static ColorTypes getColorTypesEntity(ColorTypesRequest colorTypesRequest){
        return ColorTypes.builder()
                .id(colorTypesRequest.id)
                .type(colorTypesRequest.type)
                .updated(colorTypesRequest.updated)
                .created(colorTypesRequest.created)
                .build();
    }

    public ColorTypesRequest(ColorTypes colorType) {
        this.type = colorType.getType();
        this.created = colorType.getCreated();
        this.updated = colorType.getUpdated();
    }
}
