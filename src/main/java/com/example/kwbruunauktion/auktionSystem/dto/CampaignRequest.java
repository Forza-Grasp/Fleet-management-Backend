package com.example.kwbruunauktion.auktionSystem.dto;


import com.example.kwbruunauktion.auktionSystem.entity.*;
import com.example.kwbruunauktion.auktionSystem.enums.CampaignStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CampaignRequest {

    private Long id;

    private CampaignCar campaignCar;

    private CampaignStatus campaignStatus;

    private LocalDate activeDate;

    private LocalDate localDate;

    private LcdvCodes lcdvCodes;

    private CampaignBid campaignBid;

    private CampaignColorPrice campaignColorPrice;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",shape = JsonFormat.Shape.STRING)
    private LocalDateTime created;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",shape = JsonFormat.Shape.STRING)
    private LocalDateTime updated;

    public static Campaign getCampaignEntity (CampaignRequest campaignRequest){
        return Campaign.builder()
                .id(campaignRequest.getId())
                .campaignStatus(campaignRequest.getCampaignStatus())
                .campaignCar(campaignRequest.getCampaignCar())
                .created(campaignRequest.created)
                .updated(campaignRequest.updated)
                    .build();
    }
}
