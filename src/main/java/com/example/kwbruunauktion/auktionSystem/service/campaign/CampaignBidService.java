package com.example.kwbruunauktion.auktionSystem.service.campaign;

import com.example.kwbruunauktion.auktionSystem.dto.campaign.campaignBid.CampaignBidsResponse;
import com.example.kwbruunauktion.auktionSystem.repository.campaign.CampaignBidRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampaignBidService {


    private final CampaignBidRepository campaignBidRepository;

    public CampaignBidService(CampaignBidRepository campaignBidRepository) {
        this.campaignBidRepository = campaignBidRepository;
    }

    public List<CampaignBidsResponse> getAllCampaignBids() {
        return campaignBidRepository.findAll().stream().map(CampaignBidsResponse::new).toList();
    }





}
