package com.example.kwbruunauktion.auktionSystem.service;

import com.example.kwbruunauktion.auktionSystem.dto.campaign.campaign.CampaignRequest;
import com.example.kwbruunauktion.auktionSystem.dto.campaign.campaign.CampaignResponse;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.Campaign;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.CampaignCar;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.CampaignColorPrice;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.LcdvCodes;
import com.example.kwbruunauktion.auktionSystem.repository.CampaignCarRepository;
import com.example.kwbruunauktion.auktionSystem.repository.CampaignColorPriceRepository;
import com.example.kwbruunauktion.auktionSystem.repository.CampaignRepository;
import com.example.kwbruunauktion.auktionSystem.repository.LcdvCodesRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CampaignService {
    private final CampaignRepository campaignRepository;
    private final LcdvCodesRepository lcdvCodesRepository;
    private final CampaignColorPriceRepository campaignColorPriceRepository;
    private final CampaignCarRepository campaignCarRepository;

    public CampaignService(CampaignRepository campaignRepository,
                           LcdvCodesRepository lcdvCodesRepository,
                           CampaignColorPriceRepository campaignColorPriceRepository,
                           CampaignCarRepository campaignCarRepository) {
        this.campaignRepository = campaignRepository;
        this.lcdvCodesRepository = lcdvCodesRepository;
        this.campaignColorPriceRepository = campaignColorPriceRepository;
        this.campaignCarRepository = campaignCarRepository;
    }

    public CampaignResponse getCampaignById(@PathVariable Long id) {
        return new CampaignResponse(campaignRepository.findById(id).orElseThrow(() -> new RuntimeException("CamapingID not found")));
    }

    public List<CampaignResponse> getCampaigns() {
        List<Campaign> campaigns = campaignRepository.findAll();
        return campaigns.stream().map(CampaignResponse::new).collect(Collectors.toList());
    }

    public CampaignResponse addCampaign(CampaignRequest campaignRequest) {
        if (campaignRepository.existsById(campaignRequest.getId())) {
            throw new RuntimeException("Campaign with this IS already exist");
        }
        List<LcdvCodes> lcdvCodes = lcdvCodesRepository.findAllById(campaignRequest.getLcdvCodes());
        List<CampaignColorPrice> campaignColorPrices = campaignColorPriceRepository.findAllById(campaignRequest.getCampaignColorPrices());
        CampaignCar campaignCar = campaignCarRepository.findById(campaignRequest.getCampaignCarId()).orElseThrow(() -> new RuntimeException("CampaignCar With that ID not found"));
        Campaign campaign = Campaign.builder()
                .campaignColorPrices(campaignColorPrices)
                .campaignStatus(campaignRequest.getCampaignStatus())
                .campaignCar(campaignCar)
                .activeDate(campaignRequest.getActiveDate())
                .lcdvCodes(lcdvCodes)
                .build();
        campaignRepository.save(campaign);
        return new CampaignResponse(campaign);
    }

    public void editCampaign(CampaignRequest campaignRequest, Long id) {
        Campaign campaign = campaignRepository.findById(id).orElseThrow(() -> new RuntimeException("campaign with this ID does not exist"));
        campaign.setId(campaign.getId());
        campaign.setCampaignCar(campaign.getCampaignCar());
        campaign.setCampaignStatus(campaign.getCampaignStatus());
        campaign.setActiveDate(campaign.getActiveDate());
        campaign.setCreated(campaign.getCreated());
        campaign.setUpdated(campaign.getUpdated());

        campaignRepository.save(campaign);
    }

    public void deleteCampaign(@PathVariable Long id) {
        Campaign campaign = campaignRepository.findById(id).orElseThrow(() -> new RuntimeException("campaign with this ID does not exist"));
        campaignRepository.delete(campaign);
    }
}