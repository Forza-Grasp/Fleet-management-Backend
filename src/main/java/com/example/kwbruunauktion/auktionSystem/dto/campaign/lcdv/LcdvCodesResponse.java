package com.example.kwbruunauktion.auktionSystem.dto.campaign.lcdv;


import com.example.kwbruunauktion.auktionSystem.entity.campaign.LcdvCodes;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LcdvCodesResponse {

    private Long id;

    private LcdvCodes lcdvCode;

    public LcdvCodesResponse(LcdvCodes lcdvCodes){
        this.id = lcdvCodes.getId();
        this.lcdvCode = lcdvCodes;
    }
}
