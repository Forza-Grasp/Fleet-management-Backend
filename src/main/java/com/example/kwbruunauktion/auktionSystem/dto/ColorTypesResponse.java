package com.example.kwbruunauktion.auktionSystem.dto;

import com.example.kwbruunauktion.auktionSystem.entity.ColorMix;
import com.example.kwbruunauktion.auktionSystem.entity.ColorTypes;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString

public class ColorTypesResponse {
    private Long id;
    private String type;
    private LocalDateTime created;
    private LocalDateTime updated;

    public ColorTypesResponse(ColorTypes colorType) {
        this.id = colorType.getId();
        this.type = colorType.getType();
        this.created = colorType.getCreated();
        this.updated = colorType.getUpdated();
    }
}
