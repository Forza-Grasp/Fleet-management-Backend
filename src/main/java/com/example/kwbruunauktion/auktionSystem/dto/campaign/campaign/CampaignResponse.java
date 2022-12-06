package com.example.kwbruunauktion.auktionSystem.dto.campaign.campaign;

import com.example.kwbruunauktion.auktionSystem.dto.campaign.campaignBid.CampaignBidsResponse;
import com.example.kwbruunauktion.auktionSystem.dto.campaign.campaignCarResponse.CampaignCarResponse;
import com.example.kwbruunauktion.auktionSystem.dto.campaign.campaignColor.CampaignColorPriceResponse;
import com.example.kwbruunauktion.auktionSystem.dto.campaign.lcdvCodes.LcdvCodesResponse;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.*;
import com.example.kwbruunauktion.auktionSystem.enums.CampaignStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@AllArgsConstructor
public class CampaignResponse {
    private Long id;

    private CampaignCarResponse campaignCar;

    private CampaignStatus campaignStatus;

    private LocalDate activeDate;

    private List<LcdvCodesResponse> lcdvCodes;

    private List<CampaignBidsResponse> campaignBid;

    private List<CampaignColorPriceResponse> campaignColorPrices;

    private LocalDateTime created;

    private LocalDateTime updated;

    public CampaignResponse(Campaign campaign){
        this.id = campaign.getId();
        this.campaignStatus = campaign.getCampaignStatus();
        this.activeDate = campaign.getActiveDate();
        this.created = campaign.getCreated();
        this.updated = campaign.getUpdated();
        this.campaignCar = new CampaignCarResponse(campaign.getCampaignCar());
        if (campaign.getCampaignColorPrices() != null){
            this.campaignColorPrices = campaign.getCampaignColorPrices().stream().map(CampaignColorPriceResponse::new).toList();
        }
        if (campaign.getLcdvCodes() != null){
            this.lcdvCodes = campaign.getLcdvCodes().stream().map(LcdvCodesResponse::new).toList();
        }
        if (campaign.getCampaignBids() != null){
            this.campaignBid = campaign.getCampaignBids().stream().map(CampaignBidsResponse::new).toList();
        }
        }
    }

