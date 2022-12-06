package com.example.kwbruunauktion.auktionSystem.service.campaign;

import com.example.kwbruunauktion.auktionSystem.dto.campaign.lcdvCodes.LcdvCodeRequest;
import com.example.kwbruunauktion.auktionSystem.dto.campaign.lcdvCodes.LcdvCodeResponse;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.Campaign;
import com.example.kwbruunauktion.auktionSystem.entity.campaign.LcdvCode;
import com.example.kwbruunauktion.auktionSystem.repository.campaign.CampaignRepository;
import com.example.kwbruunauktion.auktionSystem.repository.campaign.LcdvCodeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LcdvCodesService {
    private final LcdvCodeRepository lcdvCodeRepository;
    private final CampaignRepository campaignRepository;

    public LcdvCodesService(LcdvCodeRepository lcdvCodeRepository,CampaignRepository campaignRepository) {
        this.lcdvCodeRepository = lcdvCodeRepository;
        this.campaignRepository = campaignRepository;
    }

    public LcdvCodeResponse addLcdvCode(LcdvCodeRequest lcdvCodeRequest){
        if (lcdvCodeRepository.existsById(lcdvCodeRequest.getId())){
            throw new RuntimeException("LcdvCode with this id: "+lcdvCodeRequest.getId()+" already exists");
        }
        List<Campaign> attachedCampagins = campaignRepository.findAllById(lcdvCodeRequest.getCampaignResponseIds());
        LcdvCode lcdvCodeToSave = LcdvCode.builder()
                .lcdvCode(lcdvCodeRequest.getLcdvCode())
                .campaign(attachedCampagins)
                .build();

        lcdvCodeRepository.save(lcdvCodeToSave);
        return new LcdvCodeResponse(lcdvCodeToSave);
    }
}
