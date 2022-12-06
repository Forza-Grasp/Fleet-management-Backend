package com.example.kwbruunauktion.auktionSystem.api.campaign;

import com.example.kwbruunauktion.auktionSystem.dto.campaign.campaign.CampaignRequest;
import com.example.kwbruunauktion.auktionSystem.dto.campaign.campaign.CampaignResponse;
import com.example.kwbruunauktion.auktionSystem.service.campaign.CampaignService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping ("/api/campaign")
public class CampaignController {
    private final CampaignService campaignService;

    public CampaignController (CampaignService campaignService){
        this.campaignService = campaignService;
    }

    @GetMapping
    public List<CampaignResponse> getCampaigns(){
        return campaignService.getCampaigns();
    }

    @GetMapping("/{id}")
    CampaignResponse getCampaign(@PathVariable Long id){
        return campaignService.getCampaignById(id);
    }

    @DeleteMapping ("/{id}")
    ResponseEntity<Boolean> deleteCampaign (@PathVariable Long id){
        campaignService.deleteCampaign(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    CampaignResponse addCampaign( @RequestBody CampaignRequest body){
        return campaignService.addCampaign(body);
    }

    /*
    @PutMapping ("/{id}")
    ResponseEntity <Boolean> editCampaign(@PathVariable CampaignRequest campaignRequest, @PathVariable Long id){
        campaignService.editCampaign(campaignRequest, id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }


     */
}
