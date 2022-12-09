package com.example.kwbruunauktion.auktionSystem.dto.campaign.campaignBid;


import com.example.kwbruunauktion.auktionSystem.entity.campaign.CampaignBid;
import com.example.kwbruunauktion.auktionSystem.enums.CampaignBidStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CampaignBidsResponse {

    private Long userId;

    private Long campaignId;

    private CampaignBidStatus campaignBidStatus;

    private int minAmountOfCars;

    private int maxAmountOfCars;

    private double bidPrice;

    public CampaignBidsResponse(CampaignBid c) {
        this.userId = c.getUserWithRoles().getId();
        if (c.getCampaign() != null) {
            this.campaignId = c.getCampaign().getId();
        }
        this.campaignBidStatus = c.getCampaignBidStatus();
        this.minAmountOfCars = c.getMinAmountOfCars();
        this.maxAmountOfCars = c.getMaxAmountOfCars();
        this.bidPrice = c.getBidPrice();
    }


}
