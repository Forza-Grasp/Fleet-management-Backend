package com.example.kwbruunauktion.auktionSystem.dto.campaign.campaignBid;


import com.example.kwbruunauktion.auktionSystem.dto.campaign.campaign.CampaignResponse;
import com.example.kwbruunauktion.auktionSystem.dto.users.response.UserBuyerResponse;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.Campaign;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.CampaignBid;
import com.example.kwbruunauktion.auktionSystem.entity.users.UserBuyer;
import com.example.kwbruunauktion.auktionSystem.enums.CampaignBidStatus;
import com.example.kwbruunauktion.security.entity.UserWithRoles;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CampaignBidsResponse {

    private UserBuyerResponse userWithRoles;

    private CampaignResponse campaign;

    private CampaignBidStatus campaignBidStatus;

    private int minAmountOfCars;

    private int maxAmountOfCars;

    private double bidPrice;

    public CampaignBidsResponse(CampaignBid c){
        this.userWithRoles = new UserBuyerResponse((UserBuyer) c.getUserWithRoles());
        this.campaign = new CampaignResponse(c.getCampaign());
        this.campaignBidStatus = c.getCampaignBidStatus();
        this.minAmountOfCars = c.getMinAmountOfCars();
        this.maxAmountOfCars = c.getMaxAmountOfCars();
        this.bidPrice = c.getBidPrice();
    }


}
