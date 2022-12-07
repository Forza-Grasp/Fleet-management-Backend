package com.example.kwbruunauktion.auktionSystem.dto.campaign.CampaignLcdvCodeJoin;

import com.example.kwbruunauktion.auktionSystem.entity.campaign.CampaignLcdvCodeJoin;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

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
    List<Long> lcdvCodeIds;

}
