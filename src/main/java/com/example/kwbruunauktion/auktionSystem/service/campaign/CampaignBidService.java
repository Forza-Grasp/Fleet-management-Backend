package com.example.kwbruunauktion.auktionSystem.service.campaign;

import com.example.kwbruunauktion.auktionSystem.dto.campaign.campaignBid.CampaignBidsRequest;
import com.example.kwbruunauktion.auktionSystem.dto.campaign.campaignBid.CampaignBidsResponse;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.Campaign;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.CampaignBid;
import com.example.kwbruunauktion.auktionSystem.entity.users.UserBuyer;
import com.example.kwbruunauktion.auktionSystem.repository.campaign.CampaignBidRepository;
import com.example.kwbruunauktion.auktionSystem.repository.campaign.CampaignRepository;
import com.example.kwbruunauktion.auktionSystem.repository.users.UserBuyerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class CampaignBidService {


    private final CampaignBidRepository campaignBidRepository;
    private final UserBuyerRepository userBuyerRepository;
    private final CampaignRepository campaignRepository;

    public CampaignBidService(CampaignBidRepository campaignBidRepository,
                              UserBuyerRepository userBuyerRepository,
                              CampaignRepository campaignRepository) {
        this.campaignBidRepository = campaignBidRepository;
        this.userBuyerRepository = userBuyerRepository;
        this.campaignRepository = campaignRepository;
    }

    public List<CampaignBidsResponse> getAllCampaignBids() {
        return campaignBidRepository.findAll().stream().map(CampaignBidsResponse::new).toList();
    }

    public List<CampaignBidsResponse> getAllCampaignBids(Pageable p) {
        Page<CampaignBid> campaignBids = campaignBidRepository.findAll(p);
        return campaignBids.stream().map(CampaignBidsResponse::new).toList();
    }

    public List<CampaignBidsResponse> getAllCampaignBidsByCampaignId(@PathVariable long campaignId) {
        return campaignBidRepository.findAllByCampaignId(campaignId).stream().map(CampaignBidsResponse::new).toList();


    }

    public CampaignBidsResponse getCampaignBidById(@PathVariable long id) {
        return new CampaignBidsResponse(campaignBidRepository.findById(id).orElseThrow(() -> new RuntimeException("Bid with that ID not found")));
    }

    public CampaignBidsResponse createCampaignBid(CampaignBidsRequest campaignBidsRequest) {
        if (campaignBidsRequest.getCampaignId() == null || campaignBidsRequest.getUserId() == null) {
            throw new RuntimeException("Campaign ID and User ID must be provided");
        }
        Campaign foundCampaign = campaignRepository.findById(campaignBidsRequest.getCampaignId()).orElseThrow(() -> new RuntimeException("Campaign with that ID not found"));
        UserBuyer foundBuyer = userBuyerRepository.findById(campaignBidsRequest.getUserId()).orElseThrow(() -> new RuntimeException("Buyer with that ID not found"));
        CampaignBid campaignBid = CampaignBid.builder()
                .userWithRoles(foundBuyer)
                .bidPrice(campaignBidsRequest.getBidPrice())
                .minAmountOfCars(campaignBidsRequest.getMinAmountOfCars())
                .maxAmountOfCars(campaignBidsRequest.getMaxAmountOfCars())
                .build();
        campaignBidRepository.save(campaignBid);
        campaignBid.setCampaign(foundCampaign);
        campaignBidRepository.save(campaignBid);
        return new CampaignBidsResponse(campaignBid);
    }

    public CampaignBidsResponse deleteCampaignBidById(@PathVariable long id) {
        CampaignBidsResponse campaignBidsResponse = new CampaignBidsResponse(campaignBidRepository.findById(id).orElseThrow(() -> new RuntimeException("Bid with that ID not found")));
        campaignBidRepository.deleteById(id);
        return campaignBidsResponse;
    }

}
