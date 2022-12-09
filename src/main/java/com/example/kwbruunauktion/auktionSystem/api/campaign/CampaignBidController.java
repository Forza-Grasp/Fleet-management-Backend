package com.example.kwbruunauktion.auktionSystem.api.campaign;


import com.example.kwbruunauktion.auktionSystem.dto.campaign.campaignBid.CampaignBidsRequest;
import com.example.kwbruunauktion.auktionSystem.dto.campaign.campaignBid.CampaignBidsResponse;
import com.example.kwbruunauktion.auktionSystem.service.campaign.CampaignBidService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/campaign/bids")
public class CampaignBidController {

    private final CampaignBidService campaignBidService;

    public CampaignBidController(CampaignBidService campaignBidService) {
        this.campaignBidService = campaignBidService;
    }

    @GetMapping("/all")
    public List<CampaignBidsResponse> getAllCampaignBids() {
        return campaignBidService.getAllCampaignBids();
    }

    @GetMapping
    public List<CampaignBidsResponse> getAllCampaignBids(Pageable p) {
        return campaignBidService.getAllCampaignBids(p);
    }

    @GetMapping("/{id}")
    public CampaignBidsResponse getCampaignBidById(@PathVariable long id) {
        return campaignBidService.getCampaignBidById(id);
    }

    @GetMapping("/campaign/{campaignId}")
    public List<CampaignBidsResponse> getAllCampaignBidsByCampaignId(@PathVariable long campaignId) {
        return campaignBidService.getAllCampaignBidsByCampaignId(campaignId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CampaignBidsResponse createCampaignBid(@RequestBody CampaignBidsRequest campaignBidsRequest) {
        return campaignBidService.createCampaignBid(campaignBidsRequest);
    }

    @DeleteMapping("/{id}")
    public CampaignBidsResponse deleteCampaignBid(@PathVariable long id) {
        return campaignBidService.deleteCampaignBidById(id);
    }

    @PatchMapping("/{id}")
    public CampaignBidsResponse updateCampaignBid(@RequestBody CampaignBidsRequest campaignBidsRequest) {
        return campaignBidService.changeStatusOfCampaignBidById(campaignBidsRequest);
    }



}
