package com.example.kwbruunauktion.auktionSystem.api.campaign;

import com.example.kwbruunauktion.auktionSystem.dto.campaign.campaignColor.CampaignColorPriceRequest;
import com.example.kwbruunauktion.auktionSystem.dto.campaign.campaignColor.CampaignColorPriceResponse;
import com.example.kwbruunauktion.auktionSystem.service.campaign.CampaignColorPriceService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
    public List<CampaignColorPriceResponse> getAllCampaignColorPrices() {
        return campaignColorPriceService.getAllCampaignColorPrice();
    }

    @GetMapping
    public List<CampaignColorPriceResponse> getAllCampaignColorPrices(Pageable p) {
        return campaignColorPriceService.getAllCampaignColorPrice(p);
    }

    @GetMapping("/{id}")
    public CampaignColorPriceResponse getCampaignColorPriceById(@PathVariable Long id) {
        return campaignColorPriceService.getCampaignColorPriceById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCampaignColorPriceById(@PathVariable Long id) {
        campaignColorPriceService.deleteCampaignColorPrice(id);
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CampaignColorPriceResponse addCampaignColorPrice(@RequestBody CampaignColorPriceRequest campaignColorPriceRequest) {
        return campaignColorPriceService.addCampaignColorPrice(campaignColorPriceRequest);
    }

    @PatchMapping("/{id}")
    public CampaignColorPriceResponse editCampaignColorPrice(@RequestBody CampaignColorPriceRequest campaignColorPriceRequest) {
        return campaignColorPriceService.editCampaignColorPrice(campaignColorPriceRequest);
    }

}
