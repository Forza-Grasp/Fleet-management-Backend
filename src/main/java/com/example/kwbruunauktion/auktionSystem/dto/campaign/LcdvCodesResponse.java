package com.example.kwbruunauktion.auktionSystem.dto.campaign;


import com.example.kwbruunauktion.auktionSystem.entity.CampaignBid;
import com.example.kwbruunauktion.auktionSystem.entity.LcdvCodes;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

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
