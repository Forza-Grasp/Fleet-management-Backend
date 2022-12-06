package com.example.kwbruunauktion.auktionSystem.service;

import com.example.kwbruunauktion.auktionSystem.dto.campaign.campaignColor.CampaignColorPriceRequest;
import com.example.kwbruunauktion.auktionSystem.dto.campaign.campaignColor.CampaignColorPriceResponse;
import com.example.kwbruunauktion.auktionSystem.entity.BrandColorMix;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.Campaign;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.CampaignColorPrice;
import com.example.kwbruunauktion.auktionSystem.repository.BrandColorMixRepository;
import com.example.kwbruunauktion.auktionSystem.repository.campaign.CampaignColorPriceRepository;
import com.example.kwbruunauktion.auktionSystem.repository.campaign.CampaignRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class CampaignColorPriceService {

    private final CampaignColorPriceRepository campaignColorPriceRepository;
    private final BrandColorMixRepository brandColorMixRepository;
    private final CampaignRepository campaignRepository;

    public CampaignColorPriceService(CampaignColorPriceRepository campaignColorPriceRepository,
                                     BrandColorMixRepository brandColorMixRepository,
                                     CampaignRepository campaignRepository) {
        this.campaignColorPriceRepository = campaignColorPriceRepository;
        this.brandColorMixRepository = brandColorMixRepository;
        this.campaignRepository = campaignRepository;
    }


    public List<CampaignColorPriceResponse> getAllCampaignColorPrice() {
        return campaignColorPriceRepository.findAll().stream().map(CampaignColorPriceResponse::new).toList();
    }

    public List<CampaignColorPriceResponse> getAllCampaignColorPrice(Pageable p) {
        Page<CampaignColorPrice> campaignColorPrices = campaignColorPriceRepository.findAll(p);
        return campaignColorPrices.stream().map(CampaignColorPriceResponse::new).toList();
    }

    public CampaignColorPriceResponse getCampaignColorPriceById(@PathVariable Long id) {
        return new CampaignColorPriceResponse(campaignColorPriceRepository.findById(id).orElseThrow(() -> new RuntimeException("CampaignColorPrice with that ID not found")));
    }

    public CampaignColorPriceResponse addCampaignColorPrice(CampaignColorPriceRequest campaignColorPriceRequest) {
        BrandColorMix brandColorMix = brandColorMixRepository.findById(campaignColorPriceRequest.getBrandColorMixId())
                .orElseThrow(() -> new RuntimeException("BrandColorMix with that ID not found"));

        Campaign campaign = campaignRepository.findById(campaignColorPriceRequest.getCampaignId())
                .orElseThrow(() -> new RuntimeException("Campaign with that ID not found"));

        CampaignColorPrice campaignColorPrice = CampaignColorPrice.builder()
                .price(campaignColorPriceRequest.getPrice())
                .brandColorMix(brandColorMix)
                .campaign(campaign)
                .build();

        campaignColorPriceRepository.save(campaignColorPrice);

        return new CampaignColorPriceResponse(campaignColorPrice);
    }

    public CampaignColorPriceResponse getCampaignColorPriceByCampaignId(@PathVariable Long id) {
        return new CampaignColorPriceResponse(campaignColorPriceRepository.findById(id).orElseThrow(() -> new RuntimeException("CampaignColorPrice with that ID not found")));
    }

    public void deleteCampaignColorPrice(@PathVariable Long id) {
        campaignColorPriceRepository.deleteById(id);
    }

    public CampaignColorPriceResponse editCampaignColorPrice(CampaignColorPriceRequest campaignColorPriceRequest) {
        CampaignColorPrice campaignColorPrice = campaignColorPriceRepository.findById(campaignColorPriceRequest.getId())
                .orElseThrow(() -> new RuntimeException("CampaignColorPrice with that ID not found"));
        campaignColorPrice.setPrice(campaignColorPriceRequest.getPrice());
        campaignColorPriceRepository.save(campaignColorPrice);
        return new CampaignColorPriceResponse(campaignColorPrice);
    }

}
