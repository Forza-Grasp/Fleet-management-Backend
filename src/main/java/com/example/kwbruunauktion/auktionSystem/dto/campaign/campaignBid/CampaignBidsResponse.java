package com.example.kwbruunauktion.auktionSystem.dto.campaign.campaignBid;


import com.example.kwbruunauktion.auktionSystem.entity.campaign.Campaign;
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

    private UserWithRoles userWithRoles;

    private Campaign campaign;

    private CampaignBidStatus campaignBidStatus;

    private int minAmountOfCars;

    private int maxAmountOfCars;

    private double bidPrice;

}
