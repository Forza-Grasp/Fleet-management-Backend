package com.example.kwbruunauktion.auktionSystem.dto.campaign.CampaignLcdvCodeJoin;

import com.example.kwbruunauktion.auktionSystem.entity.campaign.CampaignLcdvCodeJoin;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CampaignLcdvCodeJoinRequest {
    Long id;
    Long campaignId;
    Long lcdvCodeId;

    public CampaignLcdvCodeJoinRequest(CampaignLcdvCodeJoin campaignLcdvCodeJoin) {
        this.id = campaignLcdvCodeJoin.getId();
        this.campaignId = campaignLcdvCodeJoin.getCampaign().getId();
        this.lcdvCodeId = campaignLcdvCodeJoin.getLcdvCode().getId();
    }
}
