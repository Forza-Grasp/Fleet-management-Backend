package com.example.kwbruunauktion.auktionSystem.dto.campaign.campaign;


import com.example.kwbruunauktion.auktionSystem.dto.campaign.lcdv.LcdvCodesRequest;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.Campaign;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.CampaignBid;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.CampaignCar;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.CampaignColorPrice;
import com.example.kwbruunauktion.auktionSystem.enums.CampaignStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CampaignRequest {

    private Long id;

    private Long campaignCarId;

    private CampaignStatus campaignStatus;

    private LocalDate activeDate;

    private LocalDate localDate;

    private List<Long> lcdvCodes;

    private List<Long> campaignColorPrices;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",shape = JsonFormat.Shape.STRING)
    private LocalDateTime created;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",shape = JsonFormat.Shape.STRING)
    private LocalDateTime updated;

}
