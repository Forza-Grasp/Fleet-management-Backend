package com.example.kwbruunauktion.auktionSystem.service;

import com.example.kwbruunauktion.auktionSystem.dto.campaign.campaign.CampaignRequest;
import com.example.kwbruunauktion.auktionSystem.dto.campaign.campaign.CampaignResponse;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.Campaign;
import com.example.kwbruunauktion.auktionSystem.repository.CampaignRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CampaignService {
    private final CampaignRepository campaignRepository;

    public CampaignService(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    public CampaignResponse getCampaignById(@PathVariable Long id) {
        return new CampaignResponse(campaignRepository.findById(id).orElseThrow(() -> new RuntimeException("CamapingID not found")));
    }

    public List<CampaignResponse> getCampaigns() {
        List<Campaign> campaigns = campaignRepository.findAll();
        return campaigns.stream().map(o -> new CampaignResponse(o)).collect(Collectors.toList());
    }

    public CampaignResponse addCampaign(CampaignRequest campaignRequest) {
        if (campaignRepository.existsById(campaignRequest.getId())) {
            throw new RuntimeException("Campaign with this IS already exist");
        }
        Campaign newCampaign = CampaignRequest.getCampaignEntity(campaignRequest);
        newCampaign = campaignRepository.save(newCampaign);
        return new CampaignResponse(newCampaign);
    }
    public void editCampaign (CampaignRequest campaignRequest, Long id){
        Campaign campaign = campaignRepository.findById(id).orElseThrow(()-> new RuntimeException("campaign with this ID does not exist"));
        campaign.setId(campaign.getId());
        campaign.setCampaignCar(campaign.getCampaignCar());
        campaign.setCampaignStatus(campaign.getCampaignStatus());
        campaign.setActiveDate(campaign.getActiveDate());
        campaign.setCreated(campaign.getCreated());
        campaign.setUpdated(campaign.getUpdated());

        campaignRepository.save(campaign);
    }


    public  void  deleteCampaigne ( @PathVariable Long id){
    Campaign campaign = campaignRepository.findById(id).orElseThrow(()-> new RuntimeException("campaign with this ID does not exist"));
    campaignRepository.delete(campaign);
    }
}