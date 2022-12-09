package com.example.kwbruunauktion.auktionSystem.dto.campaign.campaignColor;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CampaignColorPriceRequest {

    private Long id;

    private double price;

    private Long brandColorMixId;

    private Long campaignId;

}
