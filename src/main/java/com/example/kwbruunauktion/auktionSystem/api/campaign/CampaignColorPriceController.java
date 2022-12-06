package com.example.kwbruunauktion.auktionSystem.api.campaign;

import com.example.kwbruunauktion.auktionSystem.dto.campaign.campaignColor.CampaignColorPriceResponse;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.CampaignColorPrice;
import com.example.kwbruunauktion.auktionSystem.service.CampaignColorPriceService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/campaign/color-price")
public class CampaignColorPriceController {


    private final CampaignColorPriceService campaignColorPriceService;

    public CampaignColorPriceController(CampaignColorPriceService campaignColorPriceService) {
        this.campaignColorPriceService = campaignColorPriceService;
    }

    @GetMapping("/all")
    public List<CampaignColorPriceResponse> getAllCampaignColorPrices(){
        return campaignColorPriceService.getAllCampaignColorPrice();
    }
    @GetMapping
    public List<CampaignColorPriceResponse> getAllCampaignColorPrices(Pageable p){
        return campaignColorPriceService.getAllCampaignColorPrice(p);
    }






}
