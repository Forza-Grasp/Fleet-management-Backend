package com.example.kwbruunauktion.auktionSystem.service.campaign;

import com.example.kwbruunauktion.auktionSystem.dto.campaign.campaign.CampaignRequest;
import com.example.kwbruunauktion.auktionSystem.dto.campaign.campaign.CampaignResponse;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.Campaign;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.CampaignCar;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.CampaignColorPrice;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.LcdvCode;
import com.example.kwbruunauktion.auktionSystem.repository.campaign.CampaignCarRepository;
import com.example.kwbruunauktion.auktionSystem.repository.campaign.CampaignColorPriceRepository;
import com.example.kwbruunauktion.auktionSystem.repository.campaign.CampaignRepository;
import com.example.kwbruunauktion.auktionSystem.repository.campaign.LcdvCodeRepository;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CampaignService {
    private final CampaignRepository campaignRepository;
    private final LcdvCodeRepository lcdvCodeRepository;
    private final CampaignColorPriceRepository campaignColorPriceRepository;
    private final CampaignCarRepository campaignCarRepository;

    public CampaignService(CampaignRepository campaignRepository,
                           LcdvCodeRepository lcdvCodeRepository,
                           CampaignColorPriceRepository campaignColorPriceRepository,
                           CampaignCarRepository campaignCarRepository) {
        this.campaignRepository = campaignRepository;
        this.lcdvCodeRepository = lcdvCodeRepository;
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
        List<CampaignColorPrice> campaignColorPrices = campaignColorPriceRepository.findAllById(campaignRequest.getCampaignColorPrices());
        CampaignCar campaignCar = campaignCarRepository.findById(campaignRequest.getCampaignCarId()).orElseThrow(() -> new RuntimeException("CampaignCar With that ID not found"));
        Campaign campaign = Campaign.builder()
                .campaignColorPrices(campaignColorPrices)
                .campaignStatus(campaignRequest.getCampaignStatus())
                .campaignCar(campaignCar)
                .activeDate(campaignRequest.getActiveDate())
                .build();
        campaignRepository.save(campaign);
        return new CampaignResponse(campaign);
    }

    public void editCampaign(CampaignRequest campaignRequest) {
        Campaign campaign = campaignRepository.findById(campaignRequest.getId()).orElseThrow(() -> new RuntimeException("campaign with this ID does not exist"));
        if (campaignRequest.getCampaignColorPrices() != null) {
            List<CampaignColorPrice> campaignColorPrices = campaignColorPriceRepository.findAllById(campaignRequest.getCampaignColorPrices());
            campaign.setCampaignColorPrices(campaignColorPrices);
        }
        if (campaignRequest.getCampaignCarId() != null) {
            CampaignCar campaignCar = campaignCarRepository.findById(campaignRequest.getCampaignCarId()).orElseThrow(() -> new RuntimeException("CampaignCar With that ID not found"));
            campaignCar.setCampaign(campaign);
            campaignCarRepository.save(campaignCar);
        }
        if (campaignRequest.getCampaignStatus() != null) {
            campaign.setCampaignStatus(campaignRequest.getCampaignStatus());
        }
        if (campaignRequest.getActiveDate() != null) {
            campaign.setActiveDate(campaignRequest.getActiveDate());
        }
        campaignRepository.save(campaign);
    }

    public void deleteCampaign(@PathVariable Long id) {
        Campaign campaign = campaignRepository.findById(id).orElseThrow(() -> new RuntimeException("campaign with this ID does not exist"));
        campaignRepository.delete(campaign);
    }

    public CampaignResponse changeCampaignStatus(CampaignRequest campaignRequest) {
        Campaign campaign = campaignRepository.findById(campaignRequest.getId()).orElseThrow(() -> new RuntimeException("campaign with this ID does not exist"));
        campaign.setCampaignStatus(campaignRequest.getCampaignStatus());
        campaignRepository.save(campaign);
        return new CampaignResponse(campaign);
    }
}