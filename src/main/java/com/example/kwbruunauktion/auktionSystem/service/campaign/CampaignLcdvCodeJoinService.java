package com.example.kwbruunauktion.auktionSystem.service.campaign;

import com.example.kwbruunauktion.auktionSystem.dto.campaign.CampaignLcdvCodeJoin.CampaignLcdvCodeJoinRequest;
import com.example.kwbruunauktion.auktionSystem.dto.campaign.CampaignLcdvCodeJoin.CampaignLcdvCodeJoinResponse;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.Campaign;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.CampaignLcdvCodeJoin;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.LcdvCode;
import com.example.kwbruunauktion.auktionSystem.repository.campaign.CampaignLcdvCodeJoinRepository;
import com.example.kwbruunauktion.auktionSystem.repository.campaign.CampaignRepository;
import com.example.kwbruunauktion.auktionSystem.repository.campaign.LcdvCodeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampaignLcdvCodeJoinService {
    private final LcdvCodeRepository lcdvCodeRepository;
    private final CampaignRepository campaignRepository;
    private final CampaignLcdvCodeJoinRepository campaignLcdvCodeJoinRepository;

    public CampaignLcdvCodeJoinService(LcdvCodeRepository lcdvCodeRepository,
                                       CampaignRepository campaignRepository,
                                       CampaignLcdvCodeJoinRepository campaignLcdvCodeJoinRepository) {
        this.lcdvCodeRepository = lcdvCodeRepository;
        this.campaignRepository = campaignRepository;
        this.campaignLcdvCodeJoinRepository = campaignLcdvCodeJoinRepository;
    }

    public CampaignLcdvCodeJoinResponse addCampaignLcdvCodeJoin(CampaignLcdvCodeJoinRequest campaignLcdvCodeJoinRequest){
        LcdvCode lcdvCodeToAdd = lcdvCodeRepository.findById(campaignLcdvCodeJoinRequest.getLcdvCodeId()).orElseThrow(() ->new RuntimeException("LcdvCode not found"));
        Campaign campaignToAdd = campaignRepository.findById(campaignLcdvCodeJoinRequest.getCampaignId()).orElseThrow(() ->new RuntimeException("Campaign not found"));
        CampaignLcdvCodeJoin campaignLcdvCodeJoinToSave = CampaignLcdvCodeJoin.builder()
                .lcdvCode(lcdvCodeToAdd)
                .campaign(campaignToAdd)
                .build();
        campaignLcdvCodeJoinRepository.save(campaignLcdvCodeJoinToSave);
        return new CampaignLcdvCodeJoinResponse(campaignLcdvCodeJoinToSave);
    }

    public List<CampaignLcdvCodeJoinResponse> getAllCampaignLcdvCodeJoins(){
        return campaignLcdvCodeJoinRepository.findAll().stream().map(CampaignLcdvCodeJoinResponse::new).toList();
    }

    public CampaignLcdvCodeJoinResponse getCampaignLcdvCodeJoinById(Long id){
        return new CampaignLcdvCodeJoinResponse(campaignLcdvCodeJoinRepository.findById(id).orElseThrow(() ->new RuntimeException("CampaignLcdvCodeJoin not found")));
    }

    public CampaignLcdvCodeJoinResponse deleteCampaignLcdvCodeJoinById(Long id){
        CampaignLcdvCodeJoin campaignLcdvCodeJoinToDelete = campaignLcdvCodeJoinRepository.findById(id).orElseThrow(() ->new RuntimeException("CampaignLcdvCodeJoin not found"));
        campaignLcdvCodeJoinRepository.delete(campaignLcdvCodeJoinToDelete);
        return new CampaignLcdvCodeJoinResponse(campaignLcdvCodeJoinToDelete);
    }
}
