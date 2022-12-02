package com.example.kwbruunauktion.auktionSystem.dto.campaign;

import com.example.kwbruunauktion.auktionSystem.entity.*;
import com.example.kwbruunauktion.auktionSystem.enums.CampaignStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@AllArgsConstructor
public class CampaignResponse {
    private Long id;

    private CampaignCar campaignCar;

    private CampaignStatus campaignStatus;

    private LocalDate activeDate;

    private LcdvCodesRequest lcdvCodes;

    private CampaignBid campaignBid;

    private CampaignColorPrice campaignColorPrice;

    private LocalDateTime created;

    private LocalDateTime updated;

    public CampaignResponse(Campaign campaign){
        this.id = campaign.getId();
        this.campaignCar = campaign.getCampaignCar();
        this.campaignStatus = campaign.getCampaignStatus();
        this.activeDate = campaign.getActiveDate();
        this.created = campaign.getCreated();
        this.updated = campaign.getUpdated();
    }
}
