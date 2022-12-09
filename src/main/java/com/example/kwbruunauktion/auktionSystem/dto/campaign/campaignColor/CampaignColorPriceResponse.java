package com.example.kwbruunauktion.auktionSystem.dto.campaign.campaignColor;

import com.example.kwbruunauktion.auktionSystem.dto.BrandColorMixResponse;
import com.example.kwbruunauktion.auktionSystem.entity.BrandColorMix;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.Campaign;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.CampaignColorPrice;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@AllArgsConstructor
public class CampaignColorPriceResponse {


    private Long id;

    private BrandColorMixResponse brandColorMix;

    private Long campaignId;

    private double price;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
    private LocalDateTime created;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
    private LocalDateTime updated;


    public CampaignColorPriceResponse(CampaignColorPrice c){
        this.id = c.getId();
        this.brandColorMix = new BrandColorMixResponse(c.getBrandColorMix());
        if (c.getCampaign() != null){
            this.campaignId = c.getCampaign().getId();
        }
        this.created = c.getCreated();
        this.updated = c.getUpdated();
        this.price = c.getPrice();
    }

}
