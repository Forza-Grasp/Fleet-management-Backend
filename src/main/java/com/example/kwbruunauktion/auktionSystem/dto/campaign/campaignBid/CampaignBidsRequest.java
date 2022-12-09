package com.example.kwbruunauktion.auktionSystem.dto.campaign.campaignBid;

import com.example.kwbruunauktion.auktionSystem.entity.campaign.Campaign;
import com.example.kwbruunauktion.auktionSystem.enums.CampaignBidStatus;
import com.example.kwbruunauktion.security.entity.UserWithRoles;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CampaignBidsRequest {

    private long bidId;
    private Long userId;

    private Long campaignId;

    private CampaignBidStatus campaignBidStatus;

    private int minAmountOfCars;

    private int maxAmountOfCars;

    private double bidPrice;
}
