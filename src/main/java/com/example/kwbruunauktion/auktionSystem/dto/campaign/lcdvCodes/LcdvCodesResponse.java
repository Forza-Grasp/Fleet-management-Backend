package com.example.kwbruunauktion.auktionSystem.dto.campaign.lcdvCodes;


import com.example.kwbruunauktion.auktionSystem.dto.campaign.campaign.CampaignResponse;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.LcdvCodes;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LcdvCodesResponse {
    private Long id;
    private String lcdvCode;
    private List<CampaignResponse> campaignResponses;
    private LocalDateTime created;
    private LocalDateTime updated;

    public LcdvCodesResponse(LcdvCodes lcdvCode) {
        this.id = lcdvCode.getId();
        this.lcdvCode = lcdvCode.getLcdvCode();
        if (lcdvCode.getCampaign() != null) {
            this.campaignResponses = lcdvCode.getCampaign().stream().map(CampaignResponse::new).collect(Collectors.toList());
        }
        this.created = lcdvCode.getCreated();
        this.updated = lcdvCode.getUpdated();
    }
}
